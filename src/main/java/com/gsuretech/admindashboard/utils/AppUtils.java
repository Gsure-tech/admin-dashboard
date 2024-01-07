package com.gsuretech.admindashboard.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    //passworD2@, min =8, max = 20
    public static boolean isPasswordValid(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        if(password == null){
            return false;
        }

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }


    public static void main(String[] args) {
        System.out.println(isPasswordValid("password12@"));
    }
}
