package com.example.myapplication.activity.components

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.example.myapplication.R
import com.example.myapplication.data.GetHotListData
import com.example.myapplication.databinding.ActivityHotListBinding
import com.example.myapplication.recharge.adapter.HotListAdapter
import com.google.gson.Gson


class HotListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String = application.assets.open("getHotListData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val hotList = gson.fromJson(json, GetHotListData::class.java)
        val myAdapter = HotListAdapter(hotList.hotList)
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            //(layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myAdapter
        }
        val onItemDragListener = object : OnItemDragListener {
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

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {
                // Implementation for onItemDragEnd
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
}