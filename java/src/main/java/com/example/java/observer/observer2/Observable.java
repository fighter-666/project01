package com.example.java.observer.observer2;

/**
 * 抽象被观察者
 */
interface Observable {
    /**
     * 添加观察者
     */
    void add(Observer observer);

    /**
     * 删除观察者
     */
    void remove(Observer observer);

    /**
     * 通知观察者
     */
    void notify(String message);
}
