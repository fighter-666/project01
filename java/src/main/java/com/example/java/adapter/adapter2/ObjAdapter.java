package com.example.java.adapter.adapter2;

/**
 * 对象适配器模式，持有源类的对象，把继承关系改变为组合关系
 */
class ObjAdapter implements IDC{
   private AC ac;

   public ObjAdapter(AC ac) {
      this.ac = ac;
   }

   public int outputAC(){
      return ac.outputAC();
   }

   @Override
   public int outputDC() {
      return ac.outputAC()/44;
   }

   public static void main(String[] args) {
      ObjAdapter adapter = new ObjAdapter(new AC());
      System.out.println("交流电电压："+ adapter.outputAC());
      System.out.println("直流电电压："+ adapter.outputDC());
   }
}
