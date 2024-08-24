package com.example.java.model;

class LiveTest {
   public static void main(String[] args) {
      TencentLivePlay tencentLivePlay = new TencentLivePlay();
      tencentLivePlay.seeLivePlay();

      System.out.println("");

      JinShanLivePlay jinShanLivePlay = new JinShanLivePlay();
      jinShanLivePlay.seeLivePlay();
   }
}
