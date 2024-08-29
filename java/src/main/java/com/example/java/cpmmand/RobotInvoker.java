package com.example.java.cpmmand;

import java.util.ArrayList;
import java.util.List;

public class RobotInvoker {
   private final List<Command> sexRobotCommands = new ArrayList<>();

   public void clearCommand(){
      sexRobotCommands.clear();
   }

   public void addCommand(Command command){
      sexRobotCommands.add(command);
   }

   public void executeCommands(){
      for(Command command : sexRobotCommands){
         command.execute(); //执行命令
      }
   }
}
