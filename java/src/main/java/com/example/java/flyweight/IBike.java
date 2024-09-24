package com.example.java.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 *
 * 使用共享对象可以有效支持大量的细粒度的对象
 *
 * 享元模式是结构型模式
 * 享元模式是池技术的重要实现方式，他可以减少重复对象的创建，使用缓存来共享对象，从而降低内存的使用
 * 细粒度的对象其状态可以分为两种：内部状态和外部状态
 *
 * 内部状态：对象可共享出来的信息，存储在享元对象内部状态并且不会随环境的改变而改变。
 * 外部状态：对象依赖的一个标记是随环境改变而改变的，并且不可共享
 *
 * 共享对象的状态分为内部状态与外部状态，内部状态在各个对象间共享，而外部状态由客户端传入，这一点一定要牢记
 */
interface IBike {
    void billing(int time);
}

class ShareBike implements IBike{
    private int price = 1;
    private int total;
    @Override
    public void billing(int time) {
        total = price * time;
        System.out.println("汽车花费了"+total+"元");
    }
}

class BikeFactory{
    private static Map<String, IBike> pool = new HashMap<>();

    public IBike getBike(String name){
        IBike bike = null;
        if (pool.containsKey(name)){
            System.out.println("押金已交，直接用车："+name);
            bike = pool.get(name);
        } else {
            bike= new ShareBike();
            pool.put(name,bike);
            System.out.println(name+ "交100押金，可以用车了");
        }
        return bike;
    }
}

class Test{
    public static void main(String[] args) {
        BikeFactory factory = new BikeFactory();
        IBike ofo = factory.getBike("ofo");
        ofo.billing(2);
        IBike mobile = factory.getBike("mobile");
        mobile.billing(1);
        IBike ofo1 = factory.getBike("ofo");
        ofo1.billing(3);
    }
}
