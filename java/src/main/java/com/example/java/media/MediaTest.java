package com.example.java.media;

class MediaTest {
   public static void main(String[] args) {
      test();
   }

   public static void test() {
      Lianjia houseMediator = new Lianjia();
      Purchaser purchaser = new Purchaser(houseMediator);
      Landlord landlord = new Landlord(houseMediator);

      houseMediator.setLandlord(landlord);
      houseMediator.setPurchaser(purchaser);

      landlord.send("出售一套别墅");
      System.out.println("---------------------");
      purchaser.send("求购一套学区房");
   }
}
