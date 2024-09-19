package com.example.java.factory;

/**
 * Product (抽象产品类)：要创建的复杂对象，定义对象的公共接口
 *
 * ConcreteProduct (具体产品类)：实现Product 接口
 *
 * Factory (工厂类)：返回ConcreteProduct 实例
 */
public abstract class Product {
   public abstract void show();
}
