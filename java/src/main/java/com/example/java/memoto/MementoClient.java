package com.example.java.memoto;

class MementoClient {
   public static void main(String[] args) {
      replayGame();
   }

   public static void replayGame(){
      GameOriginator originator = new GameOriginator();
      GameCareTaker careTaker = new GameCareTaker();

      originator.playGame();
      careTaker.saveMemento(originator.saveProcess());

      originator.exitGame();

      originator.restoreProcess(careTaker.getMemento(0));
      originator.playGame();
   }
}
