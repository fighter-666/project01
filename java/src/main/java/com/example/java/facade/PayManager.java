package com.example.java.facade;

public class PayManager {
   public void pay(int money){
      System.out.println("生成订单信息");
      System.out.println("选择支付方式");
      System.out.println("支付成功：" + money + "元");
   }
}
