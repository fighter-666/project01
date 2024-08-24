package com.example.java.construct;

/**
 * 在 Computer 中创建一个静态内部类 Builder，然后将 Computer 中的参数都复制到 Builder 类中
 * 在 Computer 中创建一个 private 的构造函数，参数为 Builder 类型
 * 在 Builder 中创建一个 public 的构造函数，参数为 Computer 中必填的那些参数，cpu 和 ram
 * 在 Builder 中创建设置函数，对 Computer 中那些可选参数进行赋值，返回值为 Builder 类型的实例
 * 在 Builder 中创建一个 build() 方法，在其中构建 Computer 的实例并返回
 *
 * Computer computer = new Computer.Builder("因特尔","三星")
 *         .setDisplay("三星24寸")
 *         .setKeyboard("罗技")
 *         .setUsbCount(2)
 *         .build();
 * ————————————————
 */
public class Computer {
   private final String cpu;
   private final String ram;
   private final int ubsCount;
   private final String keyboard;
   private final String display;

   public Computer(Builder builder) {
      this.cpu = builder.cpu;
      this.ram = builder.ram;
      this.ubsCount = builder.ubsCount;
      this.keyboard = builder.keyboard;
      this.display = builder.display;
   }

   public static class Builder{
      private String cpu;
      private String ram;
      private int ubsCount;
      private String keyboard;
      private String display;

      public Builder(String cpu, String ram) {
         this.cpu = cpu;
         this.ram = ram;
      }

      public Builder setUbsCount(int ubsCount) {
         this.ubsCount = ubsCount;
         return this;
      }

      public Builder setKeyboard(String keyboard){
         this.keyboard = keyboard;
         return this;
      }

      public Builder setDisplay(String display){
         this.display = display;
         return this;
      }

      public Computer builde(){
         return new Computer(this);
      }
   }
}
