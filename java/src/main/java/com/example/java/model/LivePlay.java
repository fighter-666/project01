package com.example.java.model;

public abstract class LivePlay {
   /**
    * 模版方法
    */
   public final void seeLivePlay(){
      login();
      openRoom();
      startAudioAndVideoStream();
      pushVideoStream();
      stopAudioAndVideoStream();
      closeRoom();
   }

   private void login(){
      System.out.println("登录");
   }

   public abstract void openRoom();
   public abstract void startAudioAndVideoStream();
   public abstract void stopAudioAndVideoStream();
   public abstract void closeRoom();
   public void pushVideoStream(){

   }
}
