package com.example.java.cpmmand;

class DogWang2Clientr {
   public static void main(String[] args) {
      enjoySexRobot();
   }
   public static void enjoySexRobot() {
      RobotInvoker invoker = new RobotInvoker();

      FanBingBingReceiver fanBingBingReceiver = new FanBingBingReceiver();
      TuoXiongZhaoCommand tuoXiongZhaoCommand = new TuoXiongZhaoCommand(fanBingBingReceiver);
      TuoKuZiCommand tuoKuZiCommand = new TuoKuZiCommand(fanBingBingReceiver);

      invoker.addCommand(tuoXiongZhaoCommand);
      invoker.addCommand(tuoKuZiCommand);
      invoker.executeCommands();

      YangMIReceiver yangMIReceiver = new YangMIReceiver();
      TiaoLaWuCommand tiaoLaWuCommand = new TiaoLaWuCommand(yangMIReceiver, "半小时");
      invoker.clearCommand();
      invoker.addCommand(tiaoLaWuCommand);
      invoker.executeCommands();
   }
}
