package com.example.java.componet.componetSafe;

class Test2 {
   public static void main(String[] args) {
      test();
   }

   public static void test() {
      Column root = new Column("网站网页");
      Column music = new Column("音乐");
      Column video = new Column("视频");
      PageElement ad = new Column("广告");

      root.addPageElement(music);
      root.addPageElement(video);
      root.addPageElement(ad);

      Column chineseMusic = new Column("国语");
      Column cantonseMusic = new Column("粤语");
      music.addPageElement(chineseMusic);
      music.addPageElement(cantonseMusic);

      chineseMusic.addPageElement(new Content("十年"));
      cantonseMusic.addPageElement(new Content("明年"));

      video.addPageElement(new Content("变形记"));

      root.print("");
   }
}
