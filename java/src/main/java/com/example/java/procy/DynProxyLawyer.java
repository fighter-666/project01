package com.example.java.procy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 在java 的动态代理机制中，有两个重要的内核接口，一个是 InvocationHandler 接口、
 * 另一个则是Proxy 类，这个类和接口是实现动态代理所必须用到的
 *
 * 区别：
 * InvocationHandler 接口是给动态代理类实现的，负责处理被代理对象的操作，
 * 而Proxy 类是用来创建动态代理类实例对象，因为只有得到了这个对象我们才能动态指定代理律师是如何实现的
 */
class DynProxyLawyer implements InvocationHandler {

   private Object target;

   public DynProxyLawyer(Object object) {
      this.target = object;
   }

   @Override
   public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
      System.out.println("案件进展："+method.getName());
      Object result = method.invoke(target, objects);
      return result;
   }
}
