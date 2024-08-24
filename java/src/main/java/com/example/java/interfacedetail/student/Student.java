package com.example.java.interfacedetail.student;

class Student {
   private String name = null;

   public Student(String name) {
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   private int calcADD(int a, int b){
      return a + b;
   }

   public class doHomeWork implements doJob{

      @Override
      public void fillBlank(int a, int b, int result) {
         System.out.println(name+"求助小红计算： a + b = " + result);
      }
   }

   public void callHelp(int a, int b){
      new Calculator().add(a,b,new doHomeWork());
   }

   public void fillBlank(int a, int b, int result){
//      int result = useCalculator(a,b);
      System.out.println(name+": a + b = " + result);
   }
}
