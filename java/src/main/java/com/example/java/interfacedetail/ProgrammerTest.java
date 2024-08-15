package com.example.java.interfacedetail;

class ProgrammerTest {
   public static void main(String[] args) {
      ProgrammerB programmerB = new ProgrammerB();

      ProgrammerA programmerA = new ProgrammerA(programmerB);

      programmerA.doEvent("编写一个列表界面");
   }
}
