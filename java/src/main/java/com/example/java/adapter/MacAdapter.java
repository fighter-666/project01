package com.example.java.adapter;

/**
 * 接口适配器模式
 * 设计一个中间类去把目标接口的所有方法实现，然后适配器类再去继承这个中间类，选择性重写我所需要的方法
 */
interface IDCOutput{
   public int output5V();
   public int output12V();
   public int output20V();
}
class DefaultAdapter implements IDCOutput{
   @Override
   public int output5V() {
      return 0;
   }

   @Override
   public int output12V() {
      return 0;
   }

   @Override
   public int output20V() {
      return 0;
   }
}

public class MacAdapter extends DefaultAdapter{
   private AC ac;

   public MacAdapter(AC ac) {
      this.ac = ac;
   }

   @Override
   public int output20V() {
      return ac.outputAC()/11;
   }

   public static void main(String[] args) {
      MacAdapter adapter = new MacAdapter(new AC());
      System.out.println("mac电脑电压："+adapter.output20V());
   }
}
