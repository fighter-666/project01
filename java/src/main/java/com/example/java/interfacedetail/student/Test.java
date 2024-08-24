package com.example.java.interfacedetail.student;

class Test {
   public static void main(String[] args) {
      int a= 168;
      int b = 291;
      Student s = new Student("张三");
      s.callHelp(a, b);

      int c= 26497;
      int d= 11256;
      Seller s2 = new Seller("老婆婆");
      s2.callHelp(c, d);

   }
}
