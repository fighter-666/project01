package com.example.myapplication.activity.components

import com.example.myapplication.room3.HotDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.example.myapplication.activity.MyApplication
import com.example.myapplication.data.GetHotListData
import com.example.myapplication.databinding.ActivityHotListBinding
import com.example.myapplication.recharge.adapter.HotListAdapter
import com.example.myapplication.room3.Hot
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class HotListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotListBinding
    private var myList = mutableListOf<GetHotListData.HotListBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String = application.assets.open("getHotListData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val hotList = gson.fromJson(json, GetHotListData::class.java)

       /*     for (i in 0 until hotList.hotList.size){
                val item = hotList.hotList[i]
                if (item.type == "1"){
                    myList.add(item)
                }
            }

        insertCard(hotList.hotList)

        val newList: List<Hot> = HotDatabase.getDatabase().hotDao().update()*/


        val myAdapter = HotListAdapter(hotList.hotList)
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //(layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myAdapter
        }

        binding.btnInsert.setOnClickListener {

        }


        //hotDao.addBatchUser(mutableList)

        val onItemDragListener = object : OnItemDragListener {
            //private val cardList: MutableList<GetHotListData> = mutableListOf()
            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
                // Implementation for onItemDragStart
            }

            override fun onItemDragMoving(
                source: RecyclerView.ViewHolder,
                from: Int,
                target: RecyclerView.ViewHolder,
                to: Int
            ) {
                // Implementation for onItemDragMoving
            }

            override  fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {
                // 获取拖拽后的新顺序
                val sortedList = mutableListOf<GetHotListData.HotListBean?>()
                for (i in 0 until myAdapter.itemCount) {
                    sortedList.add(myAdapter.getItem(i))
                }

                myAdapter
                    .updateDataSet(sortedList)
            }
        }

        val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(myAdapter)
        val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

// 开启拖拽
        myAdapter.enableDragItem(itemTouchHelper)
        myAdapter.setOnItemDragListener(onItemDragListener)

// 开启滑动删除
       // mAdapter.enableSwipeItem()
        //mAdapter.setOnItemSwipeListener(onItemSwipeListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        HotDatabase.getDatabase().hotDao().deleteAllUser()
    }

    private fun insertCard(hotList: MutableList<GetHotListData.HotListBean>){
        for (i in 0 until hotList.size){
            val item = hotList[i]
            if (true/*item.type == "2"*/){
                val hot = Hot()
                hot.cardId = item.id
                hot.type = item.type
                hot.cardOrder = i
                HotDatabase.getDatabase().hotDao().insert(hot)
                Toast.makeText(MyApplication.getContext(),"批量新增数据成功",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 获取新的（数据库没有的卡片）且标记置顶的显示在首页的卡片
     */
    private fun getNewTopShowList(hotList: MutableList<GetHotListData.HotListBean>):MutableList<GetHotListData.HotListBean>{
        val newTopList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        for (data in hotList){
            if (/*isNewCard(data.id) && */data.isTop == GetHotListData.HotListBean.IS_TOP.YES && data.isShowOnHomepage == GetHotListData.HotListBean.IS_SHOW_ON_HOMEPAGE.YES){
                newTopList.add(data)
            }
        }
        newTopList.sort()
        return newTopList
    }


     /*
     *获取本地设为显示的上架卡片顺序赋予接口卡片
     * */

    private fun getLocalShowList(hotList: MutableList<GetHotListData.HotListBean>):MutableList<GetHotListData.HotListBean>{
        val localShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val locals = HotDatabase.getDatabase().hotDao().getShowCard()
        for (data in hotList){
            for (localData in locals){
                if (data.id == localData.cardId){
                    data.order = localData.cardOrder.toString()
                    localShowList.add(data)
                }
            }
        }
        localShowList.sort()
        return localShowList
    }

    /**
     * 获取新的（数据库没有的卡片）且非置顶的显示在首页的卡片
     */
    private fun getNewShowList(hotList: MutableList<GetHotListData.HotListBean>):MutableList<GetHotListData.HotListBean>{
        val newShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        for (data in hotList){
            if (/*isNewCard(data.id) && */data.isTop != GetHotListData.HotListBean.IS_TOP.YES && data.isShowOnHomepage == GetHotListData.HotListBean.IS_SHOW_ON_HOMEPAGE.YES){
                newShowList.add(data)
            }
        }
        newShowList.sort()
        return newShowList
    }

    fun getShowCardList(hotList: MutableList<GetHotListData.HotListBean>):MutableList<GetHotListData.HotListBean>{
        val showCardList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val newTopList = getNewTopShowList(hotList)
        val localShowList = getLocalShowList(hotList)
        val newShowList = getNewShowList(hotList)

        showCardList.addAll(newTopList)
        showCardList.addAll(localShowList)
        showCardList.addAll(newShowList)
        return showCardList
    }



}