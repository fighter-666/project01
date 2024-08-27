package com.example.java.componet.componetSafe;

public abstract class PageElement {
   private String name;

   public PageElement(String name) {
      this.name = name;
   }

   public abstract void print(String placeholder);

   public String getName() {
      return name;
   }
}
