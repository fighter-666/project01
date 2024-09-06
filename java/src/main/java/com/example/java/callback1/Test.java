package com.example.java.callback1;

class Test {
   public static void main(String[] args) {
      int a = 26549;
      int b =16487;
      int c= 56;
      int d = 31;
      Student s = new Student("小明");
      Student s2 = new Student("老婆婆");
      s.callHelp(a, b);
      s2.callHelp(c, d);
   }
}
