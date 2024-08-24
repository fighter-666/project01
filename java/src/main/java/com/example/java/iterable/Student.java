package com.example.java.iterable;

public class Student {

   public Student(String name, int age) {
      this.name = name;
      this.age = age;
   }

   private String name;
   private int age;

   @Override
   public String toString() {
      return "Student{" +
              "name='" + name + '\'' +
              ", age=" + age +
              '}';
   }
}
