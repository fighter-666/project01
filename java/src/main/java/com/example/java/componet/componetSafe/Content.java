package com.example.java.componet.componetSafe;

public class Content extends PageElement{
   public Content(String name) {
      super(name);
   }

   @Override
   public void print(String placeholder) {
      System.out.println(placeholder + "——" +getName());
   }
}
