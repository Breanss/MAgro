package com.example.demo.validate;

import com.example.demo.entity.Field;
import com.example.demo.tools.Validators;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddFieldValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Field.class.isAssignableFrom((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Field field = (Field) target;
        errorController(errors, field);
    }

    public void errorController(Errors errors, Field field) {

        if (!Validators.regexValidator(field.getWojewodztwo(), "([a-zA-ZĄąĘęÓóŚśŁłŹźŻżĆćŃń\\- ]+){5}"))
            errors.rejectValue("wojewodztwo", "err_code", "Wojewodztwo nieprawidłowe!");

        if (!Validators.regexValidator(field.getGmina(), "([a-zA-ZĄąĘęÓóŚśŁłŹźŻżĆćŃń\\- ]+){3}"))
            errors.rejectValue("gmina", "err_code", "Gmina nieprawidłowa!");

        if (!Validators.regexValidator(field.getMiejscowosc(), "([a-zA-ZĄąĘęÓóŚśŁłŹźŻżĆćŃń\\- ]+){3}"))
            errors.rejectValue("miejscowosc", "err_code", "Miejscowosc nieprawidłowa!");


    }
}
