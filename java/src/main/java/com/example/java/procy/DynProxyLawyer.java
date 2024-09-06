package com.example.java.procy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
