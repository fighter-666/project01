package com.example.java.expression;

public class EqualExpression extends ArithmeticExpression {
   private ArithmeticExpression left, right;

   public EqualExpression(ArithmeticExpression left, ArithmeticExpression right) {
      this.left = left;
      this.right = right;
   }

   @Override
   public Object interpret(Context context) {
       context.put((String) left.interpret(context), (int) right.interpret(context));
      return null;
   }
}
