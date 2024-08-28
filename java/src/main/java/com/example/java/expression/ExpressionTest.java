package com.example.java.expression;

class ExpressionTest {
   public static void main(String[] args) {
      test();
   }

   public  static void test() {
      Calculator calculator = new Calculator();
      calculator.read("a = 1024");
      calculator.read("b = 512");
      System.out.println("a = 1024");
      System.out.println("b = 512");

      calculator.read("a + b");
      System.out.println("a + b = "+ calculator.calculate());
      calculator.read("a - b");
      System.out.println("a - b = "+ calculator.calculate());
   }
}
