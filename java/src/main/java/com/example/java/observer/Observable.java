package com.example.java.observer;

/**
 * 抽象被观察者
 */
public interface Observable {
    /**
     * 添加观察者
     * @param observer
     */
    void add(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    void remove(Observer observer);

    /**
     * 通知观察者
     * @param message
     */
    void notify(String message);
}
