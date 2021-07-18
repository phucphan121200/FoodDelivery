package com.example.TVK.Ultis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateString {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,14}[a-zA-Z0-9]$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,14}[a-zA-Z0-9]$";
    private static final String EMAIL_PATTERN ="^[a-zA-Z0-9_]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    private static final String PERSON_NAME_PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private static final String PHONE_PATTERN = "^[0-9]{9,10}$";
    private static final Pattern pattern_username = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern pattern_password = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern pattern_email = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern pattern_personname = Pattern.compile(PERSON_NAME_PATTERN);
    private static final Pattern pattern_phone = Pattern.compile(PHONE_PATTERN);

    public static boolean isValid(final String str, String type_string)
    {
        if(type_string.equals("username"))
        {
            Matcher matcher = pattern_username.matcher(str);
            return matcher.matches();
        }
        else if(type_string.equals("password"))
        {
            Matcher matcher = pattern_password.matcher(str);
            return matcher.matches();

        }
        else if(type_string.equals("email"))
        {
            Matcher matcher = pattern_email.matcher(str);
            return matcher.matches();
        }
        else if(type_string.equals("personname"))
        {
            Matcher matcher = pattern_personname.matcher(str);
            return matcher.matches();
        }
        else if(type_string.equals("phone"))
         {
               Matcher matcher = pattern_phone.matcher(str);
               return matcher.matches();
         }
        return false;
    }

    /*public static boolean isValid(String str,String type_string)
    {
        String STR_PATTERN =null;
        if(type_string.equals("username"))
        {
            STR_PATTERN=;
        }
        if(type_string.equals("password"))
        {
            STR_PATTERN="^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,14}[a-zA-Z0-9]$";
        }
        if(type_string.equals("email"))
        {
            STR_PATTERN="^[a-zA-Z0-9_]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        }
        if(type_string.equals("personname"))
        {
            STR_PATTERN="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
        }
        if(type_string.equals("phone"))
        {
            STR_PATTERN="^[0-9]{10,11}$";
        }
        Pattern pattern = Pattern.compile(STR_PATTERN);
                       Matcher matcher = pattern.matcher(str);
                       return matcher.matches();
    }*/
    public static boolean isValidConfirmPass(String pass, String confirmpass)
    {
        if(pass.equals(confirmpass)){
            return true;
        }
        else
        {
            return false;
        }
    }


}
