package com.example.java.facade.facade2;

/**
 * 外观模式
 */
class GameSdk {
   /**
    * 登录接口
    */
   public void login(){
      // 调用登录子系统的接口
      LoginManager loginManager = new LoginManager();
      loginManager.login();
   }

   /**
    * @param money
    *
    * 支付接口
    */
   public void pay(int money){
      // 调用支付子系统的接口
      PayManager payManager = new PayManager();
      payManager.pay(money);
   }
}

/**
 * 登录系统
 */
class LoginManager{
   public void login(){
      System.out.println("打开登录界面");
      System.out.println("进行登录操作");
      System.out.println("登陆成功");
   }
}

/**
 * 支付系统
 */
class PayManager{
   public void pay(int money){
      System.out.println("生成订单信息");
      System.out.println("选择支付方式");
      System.out.println("支付成功：" + money + "元");
   }
}

class Test{
   public static void main(String[] args) {
      // 这里是游戏研发，通过调用login（）和pay（）就能调用登录和支付，
      // 无需关心支付是使用支付宝还是微信登，这是游戏SDK里面去做的事
      GameSdk gameSdk = new GameSdk();
      gameSdk.login();
      gameSdk.pay(6);
   }
}
