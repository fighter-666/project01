package com.example.java.interfacedetail.student;

class Seller {
   private String name =null;

   public Seller(String name) {
      this.name = name;
   }

   public class doHomeWork implements doJob{

      @Override
      public void fillBlank(int a, int b, int result) {
         System.out.println(name + "求助小红算账: "+a+" + "+b+" = "+result);
      }
   }

   public void callHelp(int a, int b){
      new Calculator().add(a,b,new doHomeWork());
   }
}
