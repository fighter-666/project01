package com.example.java.facade;

class FacadeTest {
   public static void main(String[] args) {
      GameSdk gameSdk = new GameSdk();
      gameSdk.login();
      gameSdk.pay(6);
   }
}
