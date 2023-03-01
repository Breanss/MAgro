package com.example.demo.tools;


public abstract class Formater {
    public static String changeOnOnlyFirstLetter(String text){
        String tmp = text.toLowerCase();
        tmp = tmp.substring(0,1).toUpperCase()+tmp.substring(1);
        return tmp;
    }
}
