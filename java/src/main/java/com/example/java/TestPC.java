package com.example.java;

//测试：生产者消费者模型--》你用缓冲区解决：管程法

//生产者，消费者，产品，缓冲区

public class TestPC {
   public static void main(String[] args) {
      SynContainer container = new SynContainer();

      new Productor(container).start();
      new Consumer(container).start();
   }
}

//生产者
class Productor extends Thread{
   SynContainer container;

   public Productor(SynContainer container){
      this.container = container;
   }

   @Override
   public void run() {
      for (int i = 0; i < 100; i++) {
         System.out.println("生产了第"+i+"只鸡");
         container.push(new Chicken(i));
      }
   }
}

//消费者
class Consumer extends Thread{
   SynContainer container;

   public Consumer(SynContainer container){
      this.container = container;
   }

   @Override
   public void run() {
      for (int i = 0; i < 100; i++) {
         System.out.println("消费了第"+container.pop().id+"只鸡");
      }
   }
}

//产品
class Chicken{
   int id; //生产编号

   public  Chicken(int id){
      this.id = id;
   }
}

//缓冲区
class SynContainer{
   //需要一个容器大小
   Chicken[] chickens = new Chicken[10];

   //容器计数器
   int count = 0;

   //生产者放入
   public synchronized void push(Chicken chicken){
      //如果容器满了，就需要等待消费者消费
      if(count == chickens.length){
         //通知消费者消费，生产等待
         try{
            this.wait();
         }catch (InterruptedException e){
            e.printStackTrace();
         }
      }

      //如果没有满，我们就需要放入产品
      chickens[count] = chicken;
      count++;

      //可以通知消费者消费了
      this.notifyAll();

   }

   //消费者消费昌产品
   public synchronized Chicken pop(){
      //判断能否消费
      if (count==0){
         //等待生产者生产，消费者等待
         try {
            this.wait();
         }catch (InterruptedException e){
            e.printStackTrace();
         }
      }

      //如果可以消费
      count--;
      Chicken chicken = chickens[count];

      //吃完了，通知生产者生产
      this.notifyAll();

      return chicken;
   }


}