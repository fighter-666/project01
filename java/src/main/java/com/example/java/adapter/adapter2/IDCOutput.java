package com.example.java.adapter.adapter2;

/**
 * 接口适配器模式，借助中间抽象类实现目标接口所有方法，适配器选择重写
 */
interface IDCOutput {
    public int output5V();

    public int output12V();

    public int output20V();
}

abstract class DefaultAdapter implements IDCOutput{
   @Override
   public int output20V() {
      return 0;
   }

   @Override
   public int output5V() {
      return 0;
   }

   @Override
   public int output12V() {
      return 0;
   }
}

class MacAdapter extends DefaultAdapter{
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
      System.out.println("mac电脑电压：" + adapter.output20V());
   }
}

