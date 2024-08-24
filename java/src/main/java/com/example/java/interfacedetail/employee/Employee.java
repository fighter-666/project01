package com.example.java.interfacedetail.employee;

class Employee {
   private CallBackInterface callback = null;

   public void setCallback(CallBackInterface callback) {
      this.callback = callback;
   }

   public void doSomething() {
      for (int i = 0; i < 10; i++) {
         System.out.println("第【"+i+"】次执行完");
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      // 回调方法
      if (callback != null) {
         callback.execute();
      }
   }
}
