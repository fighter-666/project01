package com.example.myapplication.activity.components

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.example.myapplication.R
import com.example.myapplication.activity.MyApplication
import com.example.myapplication.data.GetHotListData
import com.example.myapplication.databinding.ActivityHotListBinding
import com.example.myapplication.recharge.adapter.HotListAdapter
import com.example.myapplication.room3.Hot
import com.example.myapplication.room3.HotDatabase
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class HotListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotListBinding
    private lateinit var myAdapter: HotListAdapter
    private lateinit var myNoAdapter: HotListAdapter
    private var isDrag: Boolean = false
    private var isUpAndDowm: Boolean = false

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
        val json2: String = application.assets.open("getHotListData2.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val hotList = gson.fromJson(json, GetHotListData::class.java)

        val topList = getShowCardList(hotList.hotList)
        val noShowCardList = getNoShowCard(hotList.hotList)

        val count = HotDatabase.getDatabase().hotDao().getRowCount()
        val localShowDragList = getLocalShowList(hotList.hotList)
        val localNoShowDragList = getLocalNoShowList(hotList.hotList)
        //val newList = getNewList(hotList.hotList)
        if (count > 0) {
            myAdapter = HotListAdapter(localShowDragList)
            myNoAdapter = HotListAdapter(localNoShowDragList)
        } else {
            insertShowCard(topList)
            myAdapter = HotListAdapter(topList)

            insertNoShowCard(noShowCardList)
            myNoAdapter = HotListAdapter(noShowCardList)
        }

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //(layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myAdapter
        }
        binding.noShowRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //(layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myNoAdapter
        }

        binding.btnInsert.setOnClickListener {
            binding.btnInsert.text = "编辑"
            isUpAndDowm = true

        }

        myAdapter.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val clickedItem = adapter.getItem(position) as GetHotListData.HotListBean
            val locals = HotDatabase.getDatabase().hotDao().getShowCard()
            Toast.makeText(this, "11111", Toast.LENGTH_SHORT).show()
            val maxTakeDownCard = HotDatabase.getDatabase().hotDao().getMaxTakeDownCard()
            locals[position].cardId?.let {
                HotDatabase.getDatabase().hotDao().updateTakeDown(
                    it
                )
            }

            val localCloseList = getLocalShowList(localShowDragList)
            insertShowCard(localCloseList)
            val topList2 = getShowCardList(hotList.hotList)
            myAdapter = HotListAdapter(topList2)
            HotDatabase.getDatabase().hotDao().updatePosition(maxTakeDownCard + 1, clickedItem.id)

            val localNoShowDragList2 = getTakeDownCard(hotList.hotList)
               val localNoShowCloseList = getTakeDownCard(localNoShowDragList2)
               insertNoShowCard(localNoShowCloseList)
               myNoAdapter = HotListAdapter(localNoShowCloseList)
            when (view.id) {
                R.id.ivClose -> {
                }
            }
        })


        val onItemDragListener = object : OnItemDragListener {
            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
                // Implementation for onItemDragStart
            }

            override fun onItemDragMoving(
                source: RecyclerView.ViewHolder,
                from: Int,
                target: RecyclerView.ViewHolder,
                to: Int,
            ) {
                // Implementation for onItemDragMoving
            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {
                // 获取拖拽后的新顺序
                val items = myAdapter.data
                val updatedItems = mutableListOf<GetHotListData.HotListBean>()

                val max = HotDatabase.getDatabase().hotDao().getMaxUpLoadCard()
                for (i in 0..max) {
                    val item = items[i]
                    updatedItems.add(item)
                }

                binding.btnInsert.setOnClickListener {
                    isDrag = true
                    insertShowCard(updatedItems)
                    binding.btnInsert.text = "开始"
                    isUpAndDowm = false
                    Toast.makeText(MyApplication.getContext(), "保存成功", Toast.LENGTH_SHORT)
                        .show()
                }

                //convertedList = convertToHotList(updatedItems) // 将 GetHotListData.HotListBean 转换为 Hot 对象列表
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


    private fun getNewList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val localShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val locals = HotDatabase.getDatabase().hotDao().getAllUsers()
        for (data in hotList) {
            for (localData in locals) {
                if (data.id == localData.cardId) {
                    data.order = localData.cardOrder.toString()
                    localShowList.add(data)
                }
            }
        }
        localShowList.sort()
        return localShowList
    }


    /**
     * 获取新的（数据库没有的卡片）且标记置顶的显示在首页的卡片
     */
    private fun getNewTopShowList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val newTopList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        for (data in hotList) {
            if (/*isNewCard(data.id) && */data.isTop == GetHotListData.HotListBean.IS_TOP.YES && data.isShowOnHomepage == GetHotListData.HotListBean.IS_SHOW_ON_HOMEPAGE.YES) {
                newTopList.add(data)
            }
        }
        newTopList.sort()
        return newTopList
    }

    /**
     * 获取新的（数据库没有的卡片）且非置顶的显示在首页的卡片
     */
    private fun getNewShowList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val newShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        for (data in hotList) {
            if (/*isNewCard(data.id) && */data.isTop != GetHotListData.HotListBean.IS_TOP.YES && data.isShowOnHomepage == GetHotListData.HotListBean.IS_SHOW_ON_HOMEPAGE.YES) {
                newShowList.add(data)
            }
        }
        newShowList.sort()
        return newShowList
    }


    /*
    *获取本地设为显示的上架卡片顺序赋予接口卡片
    * */

    private fun getLocalShowList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val localShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val locals = HotDatabase.getDatabase().hotDao().getShowCard()
        for (data in hotList) {
            for (localData in locals) {
                if (data.id == localData.cardId) {
                    data.order = localData.cardOrder.toString()
                    localShowList.add(data)
                }
            }
        }
        localShowList.sort()
        return localShowList
    }

    /**
     * 获取本地设为不显示的上架卡片
     */
    private fun getLocalNoShowList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val localNoShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val locals = HotDatabase.getDatabase().hotDao().getNoShowCard()
        for (data in hotList) {
            for (localData in locals) {
                if (data.id == localData.cardId) {
                    data.order = localData.cardOrder.toString()
                    localNoShowList.add(data)
                }
            }
        }
        localNoShowList.sort()
        return localNoShowList
    }

    private fun getTakeDownCard(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val localNoShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val locals = HotDatabase.getDatabase().hotDao().getTakeDownCard()
        for (data in hotList) {
            for (localData in locals) {
                if (data.id == localData.cardId) {
                    data.order = localData.cardOrder.toString()
                    localNoShowList.add(data)
                }
            }
        }
        localNoShowList.sort()
        return localNoShowList
    }


    fun getShowCardList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val showCardList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        val newTopList = getNewTopShowList(hotList)
        //val localShowList = getLocalShowList(hotList)
        val newShowList = getNewShowList(hotList)

        showCardList.addAll(newTopList)
        //showCardList.addAll(localShowList)
        showCardList.addAll(newShowList)
        return showCardList
    }

    /**
     * 获取新的（数据库没有的卡片）的不显示在首页的卡片
     */
    private fun getNewNoShowList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val newShowList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        for (data in hotList) {
            if (/*isNewCard(data.id) && */data.isShowOnHomepage != GetHotListData.HotListBean.IS_SHOW_ON_HOMEPAGE.YES) {
                newShowList.add(data)
            }
        }
        newShowList.sort()
        return newShowList
    }

    fun getNoShowCard(hotList: MutableList<GetHotListData.HotListBean>): MutableList<GetHotListData.HotListBean> {
        val noShowCardList: MutableList<GetHotListData.HotListBean> = mutableListOf()
        //val localNoShowList = getLocalNoShowList(hotList)
        val newNoShowList = getNewNoShowList(hotList)

        //noShowCardList.addAll(localNoShowList)
        noShowCardList.addAll(newNoShowList)
        return noShowCardList
    }

    fun convertToHotList(hotList: MutableList<GetHotListData.HotListBean>): MutableList<Hot> {
        val convertedList = mutableListOf<Hot>()

        for (item in hotList) {
            val hot = Hot()
            hot.cardId = item.id
            hot.type = item.type
            hot.cardOrder = item.order.toInt()
            convertedList.add(hot)
            Log.d("TAG", "convertToHotList: ${hot.cardOrder}")
        }

        return convertedList
    }

    private fun insertCard(hotList: MutableList<GetHotListData.HotListBean>) {
        for (i in 0 until hotList.size) {
            val item = hotList[i]
            if (true/*item.type == "2"*/) {
                val hot = Hot()
                hot.cardId = item.id
                hot.type = item.type
                hot.cardOrder = i
                HotDatabase.getDatabase().hotDao().insert(hot)
            }
        }
    }

    fun insertShowCard(hotList: MutableList<GetHotListData.HotListBean>) {
        for (i in 0 until hotList.size) {
            val item = hotList[i]
            val hot = Hot()
            hot.cardId = item.id
            hot.type = "0"
            hot.isTakeDown = "0"
            hot.cardOrder = i
            HotDatabase.getDatabase().hotDao().insert(hot)
        }
    }

    fun insertNoShowCard(hotList: MutableList<GetHotListData.HotListBean>) {
        for (i in 0 until hotList.size) {
            val item = hotList[i]
            val hot = Hot()
            hot.cardId = item.id
            hot.type = "1"
            hot.isTakeDown = "1"
            hot.cardOrder = i
            HotDatabase.getDatabase().hotDao().insert(hot)
        }
    }
}