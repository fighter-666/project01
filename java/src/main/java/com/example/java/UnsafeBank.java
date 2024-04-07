package com.example.java;

class UnsafeBank {
   public static void main(String[] args) {
      Account account = new Account(100,"结婚基金");
      Drawing you = new Drawing(account,50,"你");
      Drawing girlFriend = new Drawing(account,100,"girlFriend");

      you.start();
      girlFriend.start();

   }
}

class Account{
   int money;
   String name;

   public Account(int money, String name) {
      this.money = money;
      this.name = name;
   }
}

class Drawing extends Thread{
   Account account;
   //去了多少钱
   int drawingMoney;
   //现在手里有多少钱
   int nowMoney;

   public Drawing(Account account, int drawingMoney,String name){
      super(name);
      this.account = account;
      this.drawingMoney= drawingMoney;
   }

   @Override
   public void run() {
      synchronized (account){
         //判断是否有钱
         if (account.money-drawingMoney<0){
            System.out.println(account.name+"钱不够，无法取钱");
            return;
         }

         //sleep放大问题的发生性
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }

         //卡内余额=余额-你去的钱
         account.money = account.money-drawingMoney;
         //手里的钱=手里的钱+现在取的钱
         nowMoney = nowMoney + drawingMoney;

         System.out.println(account.name+"余额为"+account.money);
         System.out.println(this.getName()+"手里的钱："+nowMoney);

      }

   }
}