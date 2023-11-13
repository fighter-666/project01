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

    /**
     * 强制转换为float
     * @param number
     * @return
     */
    public static float parseFloat(String number){
        float num;
        try {
            num = Float.parseFloat(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }
}
