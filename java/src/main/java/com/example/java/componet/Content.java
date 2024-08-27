package com.example.java.componet;

import java.util.List;

public class Content extends PageElement{
   public Content(String name) {
      super(name);
   }

   @Override
   public void addPageElement(PageElement pageElement) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void removePageElement(PageElement pageElement) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void print(String placeholder) {
      System.out.println(placeholder + "- "+ getName());
   }
}
