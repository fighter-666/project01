package com.example.java.chain;

import java.util.Objects;

public class Manager implements BudgetHandler{

    private BudgetHandler nextHandler;
    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handler(int amount) {
        Objects.requireNonNull(nextHandler);
        if (amount<50000){
            System.out.println("经理审批通过");
            return true;
        }
        System.out.println(String.format("%d经理审批未通过",amount));
        return nextHandler.handler(amount);
    }
}
