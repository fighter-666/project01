package com.example.java.adapter.adapter2;

/**
 * 类适配器模式，继承源类，实现目标接口
 */
class AC {
   public int outputAC(){
      return 220;
   }
}

interface IDC{
   public int outputDC();
}
class ClsAdapter extends AC implements IDC{
   @Override
   public int outputDC() {
      return super.outputAC()/44;
   }

   public static void main(String[] args) {
      ClsAdapter adapter = new ClsAdapter();
      System.out.println("交流电电压：" +adapter.outputAC());
      System.out.println("直流电电压：" +adapter.outputDC());
   }
}
