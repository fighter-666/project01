package com.example.java.interfacedetail;

/**
 * 程序员A
 * 程序员A是作为上层应用身份出现的，下层应用（程序员B）是不知道
 * 有哪些方法，因此它不想被下层应用（程序员B） 调用必须实现接口
 */
public class ProgrammerA implements Callback {

   //B程序员的引用
   private ProgrammerB programmerB;

   //在构造方法中持有B程序员的引用
   public ProgrammerA(ProgrammerB programmerB) {
      this.programmerB = programmerB;
   }

   /**
    * 程序员A通过这个方法告诉程序员B任务
    * @param event
    */
   public void doEvent(final String event){
      //这里用一个线程就是异步
      new Thread(new Runnable() {
         @Override
         public void run() {
            //程序员A调用程序员B中的方法，在这里注册回调接口
            programmerB.doWork(ProgrammerA.this, event);
         }
      }).start();
   }

   /**
    * 程序员B完成任务后调用此方法告诉A，也就是程序员A的回调方法
    * @param result
    */
   @Override
   public void event(final String result) {
      System.out.println("程序员B告诉程序员A："+ result);
   }
}
