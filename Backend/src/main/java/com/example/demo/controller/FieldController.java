package com.example.demo.controller;

import com.example.demo.entity.Field;
import com.example.demo.entity.User;
import com.example.demo.model.UldkItem;
import com.example.demo.pdf.FieldPdfExporter;
import com.example.demo.pdf.TemplatePdf;
import com.example.demo.service.FieldService;
import com.example.demo.service.UserService;
import com.example.demo.tools.Formater;
import com.example.demo.uldk.AdministrativeNames;
import com.example.demo.validate.AddFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class FieldController {

    @Autowired
    private AddFieldValidator addFieldValidator;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private UserService userService;

    @InitBinder()
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(addFieldValidator);
    }

    @GetMapping({"/field"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<Field> viewFieldsUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());
        return fieldService.getFieldsUser(user);
    }

    @GetMapping({"/field/addfield/uldkitems/{id}"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<UldkItem> getUldkItems(@PathVariable String id) {
        String[] tmp = id.split("=");

        if (tmp.length == 1)
            return AdministrativeNames.getNames(tmp[0], "");
        else if (tmp.length == 2)
            return AdministrativeNames.getNames(tmp[0], tmp[1]);

        return null;
    }

    @DeleteMapping("/field/dell/{id}")
    @PreAuthorize("hasRole('User')")
    public void deleteFieldId(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());
        fieldService.deleteFieldById(id, user);
    }

    @PostMapping("/field/addfield")
    @PreAuthorize("hasRole('User')")
    public Field addField(@RequestBody @Valid Field field, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.getUserByUserName(userDetails.getUsername());
        ArrayList<Field> same = fieldService.getFieldByName(field.getName(), user);

        String fieldNumber = field.getNumber() + "." + field.getNumberRegistration();

        if (same.size() != 0 || bindingResult.hasErrors() || AdministrativeNames.getNames("Dzialka", fieldNumber) == null) {
            return null;
        }

        field.setGmina(Formater.changeOnOnlyFirstLetter(field.getGmina()));
        field.setWojewodztwo(Formater.changeOnOnlyFirstLetter(field.getWojewodztwo()));
        field.setMiejscowosc(Formater.changeOnOnlyFirstLetter(field.getMiejscowosc()));
        field.setName(Formater.changeOnOnlyFirstLetter(field.getName()));
        field.setUser(user);

        return fieldService.addField(field, user);
    }

    @PutMapping("/field/edit/{id}")
    @PreAuthorize("hasRole('User')")
    public Field editField(@PathVariable Long id, @RequestBody Field field, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.getUserByUserName(userDetails.getUsername());
        fieldService.editFieldAreaCategoryPropertyById(id, field.getArea(), user);

        return null;
    }


    @GetMapping("/field/pdf")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<InputStreamResource> generatePDFField(@AuthenticationPrincipal UserDetails user) throws IOException {

        User u = userService.getUserByUserName(user.getUsername());
        ArrayList<Field> listField = fieldService.getFieldsUser(u);
        TemplatePdf exporter = new FieldPdfExporter(listField);
        ByteArrayInputStream pdf = exporter.export();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=lcwd.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

}
