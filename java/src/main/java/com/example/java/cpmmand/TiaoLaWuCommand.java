package com.example.java.cpmmand;

public class TiaoLaWuCommand implements Command{
   private YangMIReceiver daMiMi;
   private String duration;

   public TiaoLaWuCommand(YangMIReceiver daMiMi, String duration) {
      this.daMiMi = daMiMi;
      this.duration = duration;
   }

   @Override
   public void execute() {
      System.out.println(String.format("调拉舞，持续%s秒", duration));
      daMiMi.hotDance();
   }
}
