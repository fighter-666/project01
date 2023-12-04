package com.example.myapplication.util;

import android.text.TextUtils;

import java.util.Locale;

public class UtilText {
   public static boolean isEmptyOrNull(String str) {
      return TextUtils.isEmpty(str) || str.trim().toLowerCase(Locale.US).equals("null");
   }
}
