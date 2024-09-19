package com.example.java.factory;

/**
 * Product(抽象产品类)：要创建的复杂对象，定义对象的公共接口
 *
 * ConcreteProduct(具体产品类)：实现Product 接口
 *
 * Factory (抽象工厂类)：该方法返回一个Product 类型的对象
 *
 * ConcreteFactory (具体昌产品类)：返回ConcreteFactory 实例
 */
public abstract class Factory {
    public abstract Product create();

//    public static <T extends Product> T create(Class<T> clz){
//        Product product = null;
//        try {
//            product = (Product) Class.forName(clz.getName()).newInstance();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return (T) product;
//    }

//    public static Product create(String productName) {
//        Product product = null;
//
//        switch (productName){
//            case "A":
//                product = new ProductA();
//                break;
//            case "B":
//                product = new ProductB();
//                break;
//        }
//        return product;
//    }
}
