package com.example.java.chain;

import java.util.Objects;

public class GroupLeader implements BudgetHandler{

    private BudgetHandler nextHandler;
    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handler(int amount) {
        Objects.requireNonNull(nextHandler);
        if (amount < 1000){
            System.out.println("组长批准了" + amount);
            return true;
        }
        System.out.println(String.format("%d超出组长审批范围，转交给高级管理", amount));
        return nextHandler.handler(amount);
    }
}
