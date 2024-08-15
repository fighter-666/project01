package com.example.java.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * 创建具体被观察者
 */
public class Postman implements Observable{

    /**
     * 观察者列表，保存收件人（观察者）的信息
     */
    private List<Observer> personList = new ArrayList<Observer>();

    /**
     * 添加收件人
     * @param observer
     */
    @Override
    public void add(Observer observer) {
        personList.add(observer);
    }

    /**
     * 移除收件人
     * @param observer
     */
    @Override
    public void remove(Observer observer) {
        personList.remove(observer);
    }

    /**
     * 逐一通知收件人（观察者）
     * @param message
     */
    @Override
    public void notify(String message) {
        for (Observer observer : personList){
            observer.update(message);
        }
    }
}
