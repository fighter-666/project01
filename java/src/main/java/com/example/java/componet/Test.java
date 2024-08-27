package com.example.java.componet;

class Test {
   public static void main(String[] args) {
      test();
   }
   public static void test(){
      PageElement root = new Colum("网络页面");
      PageElement music = new Colum("音乐");
      PageElement video = new Colum("视频");
      PageElement ad = new Colum("广告");
      root.addPageElement(music);
      root.addPageElement(video);
      root.addPageElement(ad);

      PageElement chineseMusic = new Colum("国语");
      PageElement cantoneseMusic = new Colum("粤语");
      music.addPageElement(chineseMusic);
      music.addPageElement(cantoneseMusic);

      chineseMusic.addPageElement(new Content("十年.mp3"));
      cantoneseMusic.addPageElement(new Content("千千阙歌.mp3"));

      video.addPageElement(new Content("海阔天空.mp4"));

      root.print("");

   }
}
