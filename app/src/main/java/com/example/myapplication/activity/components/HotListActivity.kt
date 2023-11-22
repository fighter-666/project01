package com.example.myapplication.activity.components

import HotDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.example.myapplication.activity.MyApplication
import com.example.myapplication.data.GetHotListData
import com.example.myapplication.databinding.ActivityHotListBinding
import com.example.myapplication.recharge.adapter.HotListAdapter
import com.example.myapplication.recharge.adapter.MyPagerAdapter
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.fragment.WaterfallFragment
import com.example.myapplication.room.Hot
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HotListActivity : AppCompatActivity() {
    /*val database: HotDatabase by lazy {
        HotDatabase.getDatabase(applicationContext)
    }*/
    private lateinit var binding: ActivityHotListBinding
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
        val myAdapter = HotListAdapter(hotList.hotList)
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //(layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myAdapter
        }

        // 使用数据库
        val hotDao = HotDatabase.getDatabase(applicationContext).hotDao()
        // 插入数据
        val hot = Hot(id = 1, cardId = "1", type = "0", cardOrder = 1)
        //val hot2 = Hot(id = 2, type = "0", cardOrder = 2)
        //hotDao.insert(hot)
        //hotDao.insert(hot2)
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
// 将newCard对象插入数据库
                CoroutineScope(Dispatchers.IO).launch {
                    //cardDao.insertCard(newCard)
                    //insertShowCard(hotList.hotList)
                }
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
        for (item in hotList.hotList){
            Log.d("HotListActivity", item.videoBean.title)
        }
    }
    fun insertShowCard(hotList: MutableList<GetHotListData.HotListBean>){
        for (i in 0 until hotList.size){
            val item = hotList[i]
            val hot = Hot()
            //hot.cardId = item.id
            hot.type = item.type
            hot.cardOrder = i
            HotDatabase.getDatabase(applicationContext).hotDao().insert(hot)
        }
    }
}