package com.example.java.object;

public class SingletonSatisfied {
    private static SingletonSatisfied instance;

    private SingletonSatisfied() {
    }
    public static SingletonSatisfied getInstance() {
        // 先检查实例是否存在，如果不存在才进入下面的同步块
        if (instance == null){
            //同步块，线程安全的创建实例
            synchronized (SingletonSatisfied.class){
                //再次检查实例是否存在，如果不存在才真正创建实例
                if (instance == null){
                    instance = new SingletonSatisfied();
                }
            }
        }
        return instance;
    }
}
