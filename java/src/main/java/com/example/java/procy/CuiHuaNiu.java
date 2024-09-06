package com.example.java.procy;

public class CuiHuaNiu implements ILawSuit{
   @Override
   public void submit(String proof) {
      System.out.println(String.format("老板欠薪跑路，证据如下：%s", proof));
   }

   @Override
   public void defend() {
      System.out.println(String.format("铁证如山,%s还刘翠花血汗钱","马旭"));
   }
}
