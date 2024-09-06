package com.example.java.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Test1 {
   public static void main(String[] args) {
      ExecutorService executorService = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++) {
         executorService.execute(new Runnable() {
            @Override
            public void run() {
               System.out.println(Thread.currentThread().getName());
            }
         });
      }
      executorService.shutdown();

      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            System.out.println(Thread.currentThread().getName()+ "666");
         }
      });
      thread.setDaemon(true);
   }
}
