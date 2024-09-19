package com.example.java.factory;

class FactoryB extends Factory{
   @Override
   public Product create() {
      return new ProductB();
   }
}
