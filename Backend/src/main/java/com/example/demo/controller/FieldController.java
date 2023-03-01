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
import org.slf4j.LoggerFactory;
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
import java.util.List;

@RestController
public class FieldController {

    @Autowired
    private AddFieldValidator addFieldValidator;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private UserService userService;

    @InitBinder()
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(addFieldValidator);
    }

    @GetMapping({"/field"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<Field> allFieldUser(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByUserName(userDetails.getUsername());
        ArrayList<Field> fields = new ArrayList<>();
        try {
            fields = fieldService.getFieldUser(user);
        }catch (Exception e){
            LoggerFactory.getLogger(FieldController.class).error("Failed to fetch field data");
        }finally {
            LoggerFactory.getLogger(FieldController.class).info("Fetch data "+fields.size()+" fields");
        }
        return fields;
    }

    @GetMapping({"/field/addfield/uldkitems/{id}"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<UldkItem> getUldkItems(@PathVariable String id) throws IOException {
        String[] tmp = id.split("=");
        if(tmp.length==1)
            return AdministrativeNames.getNames(tmp[0], "");
        else if(tmp.length==2)
            return AdministrativeNames.getNames(tmp[0], tmp[1]);
        return null;
    }

    @DeleteMapping("/field/dell/{id}")
    @PreAuthorize("hasRole('User')")
    public void deleteField(@PathVariable Long id) {

       try {
           fieldService.deleteFieldById(id);
       }catch (Exception e){
           LoggerFactory.getLogger(FieldController.class).error("Field deletion failed");
       }finally {
           LoggerFactory.getLogger(FieldController.class).info("Delete field id="+id);
       }
    }

    @PostMapping("/field/addfield")
    @PreAuthorize("hasRole('User')")
    public Field addField(@RequestBody @Valid Field field, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        User user = userService.getUserByUserName(userDetails.getUsername());
        ArrayList<Field> same = fieldService.getFieldByName(field.getFieldName());

        String fieldNumber=field.getFieldNumber()+"."+field.getFieldNumberRegistration();

        if(same.size()!=0 || bindingResult.hasErrors() || AdministrativeNames.getNames("Dzialka",fieldNumber)==null){
            return null;
        }

        field.setFieldGmina(Formater.changeOnOnlyFirstLetter(field.getFieldGmina()));
        field.setFieldWojewodztwo(Formater.changeOnOnlyFirstLetter(field.getFieldWojewodztwo()));
        field.setFieldMiejscowosc(Formater.changeOnOnlyFirstLetter(field.getFieldMiejscowosc()));
        field.setFieldNameField(Formater.changeOnOnlyFirstLetter(field.getFieldName()));

        Field add=new Field();
        try {
            field.setUser(user);
            add=fieldService.addField(field);
        }catch (Exception e){
            LoggerFactory.getLogger(FieldController.class).error("Field add failed");
        }
        LoggerFactory.getLogger(FieldController.class).info("Add field");
        return add;
    }


    @PutMapping("/field/edit/{id}")
    @PreAuthorize("hasRole('User')")
    public Field editField(@PathVariable Long id, @RequestBody Field field) {

        try {
            fieldService.editFieldAreaCategoryPropertyById(id, field.getFieldArea());
        }catch (Exception e){
            LoggerFactory.getLogger(FieldController.class).error("Failed to edit field");
        }
        LoggerFactory.getLogger(FieldController.class).info("Field data changed");
        return null;
    }


    @GetMapping("/field/pdf")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<InputStreamResource> generatePDFField(@AuthenticationPrincipal UserDetails user) throws IOException {

        User u = userService.getUserByUserName(user.getUsername());
        ArrayList<Field> listField = fieldService.getFieldUser(u);
        TemplatePdf exporter = new FieldPdfExporter(listField);
        ByteArrayInputStream pdf= exporter.export();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline;file=lcwd.pdf");
        LoggerFactory.getLogger(FieldController.class).info("Creating a pdf with field data!");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

}
