package com.example.java.Bridging;

public abstract class Coffee {
   protected ICoffeeAdditives additives;

   public Coffee(ICoffeeAdditives additives) {
      this.additives = additives;
   }

   public abstract void orderCoffee(int count);
}