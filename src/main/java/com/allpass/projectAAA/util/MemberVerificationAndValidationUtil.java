package com.allpass.projectAAA.util;

public class MemberVerificationAndValidationUtil {

    public static boolean MemberPasswordVerification(String password1, String password2) {

        if(password1.equals(password2)){
            return true;
        }else{
            return false;
        }
    }
}
