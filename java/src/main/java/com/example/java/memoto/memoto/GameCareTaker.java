package com.example.java.memoto.memoto;

import java.util.ArrayList;
import java.util.List;

public class GameCareTaker {
   private List<GameProgressMemento> mementos = new ArrayList<>();

   public void saveMemento(GameProgressMemento memento){
      this.mementos.add(memento);
   }

   public GameProgressMemento getMemento(int index){
      return this.mementos.get(index);
   }
}
