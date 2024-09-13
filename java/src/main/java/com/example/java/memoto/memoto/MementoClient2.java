package com.example.java.memoto.memoto;

class MementoClient2 {
   public static void main(String[] args) {
      GameOriginator originator = new GameOriginator();
      GameCareTaker careTaker = new GameCareTaker();

      originator.playGame();
      careTaker.saveMemento(originator.saveProcess());
      originator.exitGame();

      originator.restoreProcess(careTaker.getMemento(0));
      originator.playGame();
   }
}
