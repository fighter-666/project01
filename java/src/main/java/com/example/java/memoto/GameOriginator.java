package com.example.java.memoto;

public class GameOriginator {
   private int currentScore;

   /**
    * 将需要保存的状态封装在Memento里对外提供
    * @return
    */
   public GameProgressMemento saveProcess(){
      return new GameProgressMemento(currentScore);
   }

   public void restoreProcess(GameProgressMemento memento){
      currentScore = memento.getScore();
   }

   /**
    * 对内部状态的使用
    */
   public void playGame(){
      System.out.println("----游戏开始----");
      System.out.println("游戏开始，当前分数："+ currentScore);
      System.out.println("杀死一个小怪得1分");
      currentScore ++;
      System.out.println(String.format("游戏结束，总得分为：%d",currentScore));
   }

   public void exitGame(){
      System.out.println("游戏退出");
      currentScore = 0;
      System.out.println("----退出----");
   }


}
