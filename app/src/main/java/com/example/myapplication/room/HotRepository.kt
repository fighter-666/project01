package com.example.myapplication.room

import com.example.myapplication.data.GetHotListData

class HotRepository {
    fun insertShowCard(hotList: MutableList<GetHotListData.HotListBean>){
        for (i in 0 until hotList.size){
            val item = hotList[i]
            val hot = Hot()
            hot.type = "0"
            hot.cardOrder = i
            //CardDatabase.getDatabase().hotDao().insert(hot)
        }
    }
}