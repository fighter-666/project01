package com.example.java.media;

public class Lianjia implements HouseMediator{
   Purchaser mPurchaser;
   Landlord mLandlord;

   public void setPurchaser(Purchaser mPurchaser) {
      this.mPurchaser = mPurchaser;
   }

   public void setLandlord(Landlord mLandlord) {
      this.mLandlord = mLandlord;
   }

   @Override
   public void notice(Person person, String message) {
      System.out.println("中介收到信息，并转发给相应的目标人群");
      if (person == mPurchaser){
         mLandlord.getNotice(message);
      } else if (person == mLandlord){
         mPurchaser.getNotice(message);
      }
   }
}
