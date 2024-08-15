package com.example.java.interfacedetail.people;

public class Example {
   public static void main(String[] args) {
      People a;
      a = new Student();
      a.peopleList();

      a= new Teacher();
      a.peopleList();
   }
}
