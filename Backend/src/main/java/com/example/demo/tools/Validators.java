package com.example.demo.tools;

import java.util.regex.Pattern;

public abstract class Validators {
    public static Boolean regexValidator(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).matches();
    }
}
