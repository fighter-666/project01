package com.example.java.procy;

import java.lang.reflect.Proxy;

/**
 * 产生代理对象的静态代理工厂类
 */
public class ProxyFactory {
   public static Object getDynProxy(Object target){
      DynProxyLawyer handler = new DynProxyLawyer(target);
      return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
   }
}
