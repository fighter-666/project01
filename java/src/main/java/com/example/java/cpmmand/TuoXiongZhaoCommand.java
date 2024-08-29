package com.example.java.cpmmand;

public class TuoXiongZhaoCommand implements Command{
   private FanBingBingReceiver bingBing;

   public TuoXiongZhaoCommand(FanBingBingReceiver bingBing) {
      this.bingBing = bingBing;
   }

   @Override
   public void execute() {
      bingBing.tackOffBra();
   }
}
