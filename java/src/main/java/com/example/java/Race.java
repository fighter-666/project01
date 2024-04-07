package com.example.java;

public class Race implements Runnable {

   private static String winner;
   @Override
   public void run() {
      for (int i = 0; i <= 100; i++){

         //判断比赛是否结束
         boolean flag = gameOver(i);
         if (flag){
            break;
         }
         System.out.println(Thread.currentThread().getName()+"-->p跑了" + i+"步");
      }
   }

   private boolean gameOver(int step){
      //判断是否有胜利者
      if (winner!=null){//已经存在胜利者
         return true;
      }{
         if (step==100){
            winner = Thread.currentThread().getName();
            System.out.println("winner is "+winner);
            return true;
         }
      }
      return false;
   }

   public static void main(String[] args) {
      Race race = new Race();

      new Thread(race,"兔子").start();
      new Thread(race,"乌龟").start();
   }
}
