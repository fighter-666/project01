package com.example.java.adapter.adapter2;

/**
 * 设想，我现在的目标接口有多个方法，可以输出5V,12V,20V的电压。按照正常逻辑，设计一个
 * 适配器去实现这个接口，很显然需要实现所有的方法。但是，实际使用中，其实只需要使用其中一个方法就可以了
 * 比如我mac电脑直流电压20V，只需要实现20V的方法就可以了
 *
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

