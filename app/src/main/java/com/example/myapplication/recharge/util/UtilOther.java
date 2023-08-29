package com.example.myapplication.recharge.util;

public class UtilOther {
    public static int parseInt(String number){
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }
}
