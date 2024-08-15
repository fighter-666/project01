package com.example.java.interfacedetail.test1;

import java.awt.SystemTray;

public class InterfaceTest {
   public void handleThings(InterfaceExample example, String string){
      new Thread(new Runnable() {
         @Override
         public void run() {
            System.out.println("---做一些事情---");
            try {
               Thread.sleep(3000);
            } catch (InterruptedException e){
               e.printStackTrace();
            }
            example.sendMessage("接口传递的参数："+string);
         }
      }).start();
   }
}
