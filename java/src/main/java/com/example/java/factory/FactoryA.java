package com.example.java.factory;

class FactoryA extends Factory{
   @Override
   public Product create() {
      return new ProductA();
   }
}
