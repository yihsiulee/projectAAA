package com.allpass.projectAAA.util;

import java.text.SimpleDateFormat;

public class MemberIdRandomUtil {
    public static SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSS");

    public synchronized static Long randomMemberNumber(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long Id=Long.parseLong(date.format(System.currentTimeMillis()));

        return Id;
    }
}
