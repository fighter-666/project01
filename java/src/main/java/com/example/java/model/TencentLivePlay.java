package com.example.java.model;

public class TencentLivePlay extends LivePlay{
   @Override
   public void openRoom() {
      System.out.println("腾讯云直播播放器打开房间");
   }

   @Override
   public void startAudioAndVideoStream() {
      System.out.println("腾讯云直播播放器打开视频流");
   }

   @Override
   public void stopAudioAndVideoStream() {
      System.out.println("腾讯云直播播放器关闭视频流");
   }

   @Override
   public void closeRoom() {
      System.out.println("腾讯云直播播放器关闭房间");
   }

   @Override
   public void pushVideoStream() {
      super.pushVideoStream();
      System.out.println("腾讯云直播播放器进行旁路推流");
   }
}
