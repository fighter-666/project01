package com.example.java.expression;

import javax.naming.Context;

public class NumExpression extends ArithmeticExpression {
   private String strNum;

   public NumExpression(String strNum) {
      this.strNum = strNum;
   }

   @Override
   public Integer interpret(com.example.java.expression.Context context) {
      return Integer.parseInt(strNum);
   }
}
