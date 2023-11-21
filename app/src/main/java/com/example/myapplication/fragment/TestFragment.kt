package com.example.myapplication.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.example.myapplication.R
import com.example.myapplication.data.GetHotListData
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.databinding.FragmentTestBinding
import com.example.myapplication.recharge.adapter.FeedAdapter
import com.example.myapplication.recharge.adapter.HotListAdapter
import com.example.myapplication.recharge.adapter.WaterfallAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.view.property.Piggy
import com.example.myapplication.recharge.widget.GetTelephoneNumberManager
import com.example.myapplication.recharge.widget.LoadMoreManager
import com.example.myapplication.widget.BaseLazyFragment
import com.example.myapplication.widget.MyFragmentObserver
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TestFragment : BaseLazyFragment() {
    private var _binding: FragmentTestBinding? = null
    val binding get() = _binding!!
    //private lateinit var myAdapter: FeedAdapter
    private lateinit var myAdapter: WaterfallAdapter
    private var contactNumber: String? = null
    private var mIntent: Intent? = null
    private var number: Int = 0
    private lateinit var feedList: GetFeedListData
    private lateinit var tabList: GetFeedTabData

    companion object {
        private const val ARG_TAB_NAME = "tabName"
        fun newInstance(tabName: Int): TestFragment {
            val args = Bundle()
            args.putString(ARG_TAB_NAME, tabName.toString())
            args.putInt(ARG_TAB_NAME, tabName)
            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    //用于请求读取联系人权限并执行相应的操作。


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(MyFragmentObserver())
        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()



        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String = requireContext().assets.open("getHotListData.json").bufferedReader()
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
                // Implementation for onItemDragEnd
                //val sortCards = mutableListOf<GetHotListData>()
                //val cardDao = CardDatabase.getDatabase(applicationContext).cardDao()

                /* // 在适当的位置使用数据库
                 val cardDao = database.cardDao()
                 cardDao.getAllCards()*/
                /*val  cardList = cardDao.getAllCards()
                for (i in 0 until cardList.size){
                    Log.d("sortCards", "onItemDragEnd: ${cardList[i].type}")
                }*/



// 将newCard对象插入数据库
                CoroutineScope(Dispatchers.IO).launch {
                    //cardDao.insertCard(newCard)
                }


                /*for (i in 0 until  myAdapter.itemCount){
                    cardList.add(getData()[i])
                    CoroutineScope(Dispatchers.IO).launch {
                        //cardDao.insertCard(sortCards[i])
                    }

                    Log.d("sortCards", "onItemDragEnd: ${cardList[i].hotList[i].type}")
                }*/


                CoroutineScope(Dispatchers.IO).launch {
                    //cardDao.updateCard(sortCards)
                    /*cardDao.getAllCards()
                    for (card in cardDao.getAllCards()){
                        Log.d("FirstRoomActivity",card.title)
                    }*/
                }
            }
            /* // 获取数据集合
             fun getData(): List<GetHotListData> {
                 return cardList
             }*/
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

    override fun loadData() {


    }


}


