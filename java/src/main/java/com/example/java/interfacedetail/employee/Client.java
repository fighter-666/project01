package com.example.java.interfacedetail.employee;

class Client {
   public static void main(String[] args) {
      Employee employee = new Employee();

      employee.setCallback(new Boss());

      employee.doSomething();
   }
}