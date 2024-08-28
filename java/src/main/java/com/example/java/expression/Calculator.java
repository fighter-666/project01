package com.example.java.expression;

public class Calculator {
   Context mContext = new Context();
   private ArithmeticExpression mExpression;

   public void read(String expression){
      String[] split = expression.split(" ");
      switch (split[1]){
         case "=":
            new EqualExpression(new VarExpression(split[0]),
            new NumExpression(split[2])).interpret(mContext);
            break;
         case "+":
            mExpression = new AddExpresssion(new VarExpression(split[0]),
            new VarExpression(split[2]));
            break;
         case "-":
            mExpression = new SubExpression(new VarExpression(split[0]),
            new VarExpression(split[2]));
            break;
      }
   }

   public int calculate(){
      return (int) mExpression.interpret(mContext);
   }
}
