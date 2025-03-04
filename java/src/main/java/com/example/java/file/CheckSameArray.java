package com.example.java.file;

import java.util.HashMap;

public class CheckSameArray {
    public static void main(String[] args) {
        String[] strings = new String[3];
        strings[0] = "1";
        strings[1] = "2";
        strings[2] = "3";

        String[] array2 = {"1","2","3"};

        Boolean aBoolean=checkSameArray(strings,array2);
        System.out.println(aBoolean);

    }

    /**
     *  比较两个数组内容是否一样，顺序不同应该返回true
     */
    public static boolean checkSameArray(String[] a, String[] b) {
        if (a == null || b == null || a.length != b.length)
            return false;
        HashMap<String, Integer> setKw = new HashMap<>();
        for (String s :a) {
            if (setKw.containsKey(s)){
                setKw.put(s,setKw.get(s) + 1);
            } else {
                setKw.put(s,1);
            }
        }
        for (String s: b) {
            if (setKw.containsKey(s)){
                Integer kwNum = setKw.get(s);
                if (kwNum == 1){
                    setKw.remove(s);
                } else {
                    setKw.put(s, kwNum -1);
                }
            } else {
                return false;
            }
        }
        return setKw.isEmpty();

    }
}
