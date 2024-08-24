package com.example.java.thread;

public class ThreadUnsafeExample {
   private int cnt = 0;
   public void add(){
      cnt++;
   }

   public int get(){
      return cnt;
   }
}
