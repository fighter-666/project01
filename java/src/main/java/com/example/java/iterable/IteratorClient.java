package com.example.java.iterable;

import java.util.Iterator;

public class IteratorClient {
   public static void main(String[] args) {
      for (Student student : new Class()) {
         System.out.println(student);// Calls student.toString()
      }

      Class students = new Class();
      System.out.println("---开始点名---");
      Iterator<Student> iterator = students.iterator();
      while (iterator.hasNext()){
         System.out.println(iterator.next());
      }

   }
}
