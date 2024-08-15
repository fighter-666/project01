package com.example.java.interfacedetail;

import jdk.internal.org.jline.utils.Log;

/**
 * 程序员B
 * 必须要注意，这是一个底层类，底层是不了解上层服务的
 */
public class ProgrammerB {
   public void doWork(Callback callback, String event){
      System.out.println("程序员A告诉程序员B需要做的事：" +event);

      System.out.println("程序员B：干活...");

      String result = "完成工作";

      // 程序员B在这里调用A回调方法，告诉完成任务
      callback.event(result);
   }
}
