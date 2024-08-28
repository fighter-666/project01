package com.example.java.expression;

import javax.naming.Context;

class VarExpression extends ArithmeticExpression {
   private String var;

   public VarExpression(String var) {
      this.var = var;
   }


   public String interpret(com.example.java.expression.Context context) {
      return var;
   }
}
