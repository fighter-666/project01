package com.example.java.adapter;

public class AC {
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
      return outputAC()/44;
   }

   public static void main(String[] args) {
      ClsAdapter adapter = new ClsAdapter();
      System.out.println("交流电电压："+adapter.outputAC());
      System.out.println("直流电电压："+adapter.outputDC());
   }
}
