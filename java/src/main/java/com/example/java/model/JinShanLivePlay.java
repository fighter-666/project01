package com.example.java.model;

public class JinShanLivePlay extends LivePlay{
   @Override
   public void openRoom() {
      System.out.println("金山直播播放器打开房间");
   }

   @Override
   public void startAudioAndVideoStream() {
      System.out.println("金山直播播放器打开音视频流");
   }

   @Override
   public void stopAudioAndVideoStream() {
      System.out.println("金山直播播放器关闭音视频流");
   }

   @Override
   public void closeRoom() {
      System.out.println("金山直播播放器关闭房间");
   }
}
