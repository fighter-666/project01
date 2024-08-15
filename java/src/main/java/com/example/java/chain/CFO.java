package com.example.java.chain;

public class CFO implements BudgetHandler{
    private BudgetHandler nextHandler;
    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handler(int amount) {
        if(amount < 5000){
            System.out.println("CFO 审批通过");
            return true;
        }
        if (nextHandler != null){
            return nextHandler.handler(amount);
        }
        System.out.println(String.format("%dCFO 审批不通过",amount));
        return false;
    }
}
