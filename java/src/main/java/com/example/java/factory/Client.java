package com.example.java.factory;

class Client {
   public static void main(String[] args) {

      FactoryA factoryA = new FactoryA();
      Product productA = factoryA.create();
      productA.show();

      FactoryB factoryB = new FactoryB();
      factoryB.create().show();

//      Factory.create(ProductA.class).show();
//      Factory.create(ProductB.class).show();

//      Factory.create("A").show();
//      Factory.create("B").show();
//      try {
//         Factory.create("C").show();
//      } catch (NullPointerException e){
//         System.out.println("没有C");
//      }

   }
}
