package com.example.java.componet.componetSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Column extends PageElement{
   private List<PageElement> mPageElements = new ArrayList<>();
   public Column(String name) {
      super(name);
   }

   public void addPageElement(PageElement pageElement){
      mPageElements.add(pageElement);
   }

   public void removePageElement(PageElement pageElement){
      mPageElements.remove(pageElement);
   }

   public void clear(){
      mPageElements.clear();
   }

   @Override
   public void print(String placeholder) {
      System.out.println(placeholder + "└──" + getName());
      Iterator<PageElement> iterator = mPageElements.iterator();
      while (iterator.hasNext()){
         PageElement next = iterator.next();
         next.print(placeholder + "    ");// 这里使用4个空格来缩进，你也可以使用其他字符或数量
      }
   }
}
