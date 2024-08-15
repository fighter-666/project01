package com.example.java.chain;

public class DogWang2Cor {
    public static void main(String[] args) {
        GroupLeader leader = new GroupLeader();
        Manager manager = new Manager();
        CFO cfo = new CFO();

        leader.setNextHandler(manager);
        manager.setNextHandler(cfo);

        System.out.println(String.format("领导好，由于开发需求，需购买一台Mac电脑，预算为%d 望批准",20000));
        if (leader.handler(20000)){
            System.out.println("领导已批准");
        } else {
            System.out.println("领导未批准");
        }
    }
}
