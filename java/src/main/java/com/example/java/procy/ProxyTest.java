package com.example.java.procy;

/**
 * 这样就基本构建静态代理关系，然后在客户端就可以使用代理对象来进行操作了
 *
 * 输出结果如下：
 * 老板欠薪跑路，证据如下：工资流水在此
 * 铁证如山，马旭还钱
 */
class ProxyTest {
   public static void main(String[] args) {
//      ProxyFactory.getProxy().submit("工资流水在此");
//      ProxyFactory.getProxy().defend();

      ILawSuit proxy = (ILawSuit) ProxyFactory.getDynProxy(new CuiHuaNiu());
      proxy.submit("工资流水在此");
      proxy.defend();
   }
}
