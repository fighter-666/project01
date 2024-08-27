package com.example.java.facade;

public class GameSdk {
   public void login(){
      LoginManager loginManager = new LoginManager();
      loginManager.login();
   }

   public void pay(int money){
      PayManager payManager = new PayManager();
      payManager.pay(money);
   }
}
