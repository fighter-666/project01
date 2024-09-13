package com.example.java.observer.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * 快递员
 */
class Postman implements Observable {

    /**
     * 保存收件人（观察者）信息
     */
    private List<Observer> personList = new ArrayList<Observer>();

    /**
     * @param observer
     *
     * 天剑收件人
     */
    @Override
    public void add(Observer observer) {
        personList.add(observer);
    }

    /**
     * @param observer
     *
     * 移除收件人
     */
    @Override
    public void remove(Observer observer) {
        personList.remove(observer);
    }

    /**
     * @param message
     *
     * 逐一通知收件人（观察者）
     */
    @Override
    public void notify(String message) {
        for (Observer observer : personList
        ) {
            observer.update(message);

        }
    }
}
