package com.example.java.object;

/**
 * 饿汉式单例模式
 */
public class SingletonHungry {
    /**
     * 创建一个实例对象
     */
    private static SingletonHungry inatance = new SingletonHungry();

    /**
     * 私有构造方法，防止被实例化
     */
    private SingletonHungry() {
    }

    /**
     * 静态get方法
     * @return
     */
    public static SingletonHungry getInstance() {

        return inatance;
    }
}
