package com.example.java.observer;

/**
 * 抽象观察者
 */
public interface Observer {
    /**
     * 更新方法
     * @param message
     */
    public void update(String message);
}
