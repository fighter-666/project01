package com.example.java.componet;

import java.util.Iterator;
import java.util.List;

public class Colum extends PageElement{
   public Colum(String name) {
      super(name);
   }

   @Override
   public void addPageElement(PageElement pageElement) {
      mPageElements.add(pageElement);
   }

   @Override
   public void removePageElement(PageElement pageElement) {
      mPageElements.remove(pageElement);
   }

   @Override
   public void clear() {
      mPageElements.clear();
   }

   @Override
   public void print(String placeholder) {
      System.out.println(placeholder + "└──" + getName());
      Iterator<PageElement> iterator = mPageElements.iterator();
      while (iterator.hasNext()){
         PageElement pageElement = iterator.next();
         pageElement.print(placeholder + "  ");
      }
   }
}
