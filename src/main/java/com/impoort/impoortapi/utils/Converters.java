package com.impoort.impoortapi.utils;

public class Converters {

    public static String generateFullName(String firstName,String lastName){
        if(lastName == null){
            return firstName;
        }else if(firstName == null){
            return  lastName;
        }else return firstName + " " + lastName;
    }
}
