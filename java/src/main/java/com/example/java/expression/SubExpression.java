package com.example.java.expression;

public class SubExpression extends ArithmeticExpression{
   private ArithmeticExpression left, right;

   public SubExpression(ArithmeticExpression left, ArithmeticExpression right) {
      this.left = left;
      this.right = right;
   }

   @Override
   public Integer interpret(Context context) {
      return context.get((String) left.interpret(context))-
              context.get((String) right.interpret(context));
   }
}
