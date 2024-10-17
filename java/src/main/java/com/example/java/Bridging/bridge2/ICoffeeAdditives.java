package com.example.java.Bridging.bridge2;

import java.util.Random;

interface ICoffeeAdditives {
   void addSomething();
}

abstract class Coffee{
   protected ICoffeeAdditives additives;

   public Coffee(ICoffeeAdditives additives) {
      this.additives = additives;
   }

   public abstract void orderCoffee(int count);
}

abstract class RefindCoffee extends Coffee{

   public RefindCoffee(ICoffeeAdditives additives) {
      super(additives);
   }

   public void checkQuality(){
      Random random = new Random();
      System.out.println(String.format("%s 添加%s",additives.getClass().getSimpleName(),random.nextBoolean()?"太多":"正常"));

   }
}

class Milk implements ICoffeeAdditives{
   @Override
   public void addSomething() {
      System.out.println("加奶");
   }
}

class Sugar implements ICoffeeAdditives{
   @Override
   public void addSomething() {
      System.out.println("加糖");
   }
}

class LargeCoffee extends RefindCoffee {
   public LargeCoffee(ICoffeeAdditives additives) {
      super(additives);
   }

   @Override
   public void orderCoffee(int count) {
      additives.addSomething();
      System.out.println(String.format("大杯咖啡%d杯",count));
   }
}

class SmallCoffee extends RefindCoffee{
   public SmallCoffee(ICoffeeAdditives additives) {
      super(additives);
   }

   @Override
   public void orderCoffee(int count) {
      additives.addSomething();
      System.out.println(String.format("小贝咖啡%d杯",count));
   }
}

class Test{
   public static void main(String[] args) {
      RefindCoffee largeWithMilk =new LargeCoffee(new Milk());
      
   }
}
