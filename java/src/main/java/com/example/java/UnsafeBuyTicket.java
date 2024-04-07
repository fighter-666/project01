package com.example.java;

//不安全的买票
class UnsafeBuyTicket {
   public static void main(String[] args) {
      BuyTicket buyTicket = new BuyTicket();

      new Thread(buyTicket,"小明").start();
      new Thread(buyTicket,"老师").start();
      new Thread(buyTicket,"黄牛党").start();
   }
}

class BuyTicket implements Runnable{

   private int ticketNums = 10;
   boolean flag = true;
   @Override
   public void run() {
      while (flag){
         buy();
      }

   }

   private synchronized void buy(){
      //判断是否有票
      if (ticketNums<=0){
         flag = false;
         return;
      }

      //模拟延时
      try {
         Thread.sleep(100);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }

      //买票
      System.out.println(Thread.currentThread().getName()+"买到了第"+ticketNums--+"张票");
   }
}
