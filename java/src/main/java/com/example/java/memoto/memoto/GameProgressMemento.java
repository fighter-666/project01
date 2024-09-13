package com.example.java.memoto.memoto;

public class GameProgressMemento {
   private  int score;

   public GameProgressMemento(int score) {
      this.score = score;
   }

   public int getStore(){
      return score;
   }
}
