package com.example.myapplication.recharge.fragment

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.recharge.adapter.WaterfallAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.widget.GetTelephoneNumberManager
import com.example.myapplication.recharge.widget.LoadMoreManager
import com.example.myapplication.widget.BaseLazyFragment
import com.example.myapplication.widget.MyFragmentObserver
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WaterfallFragment : BaseLazyFragment() {
    private var _binding: FragmentRechargeWaterfallBinding? = null
    val binding get() = _binding!!
    private lateinit var myAdapter: WaterfallAdapter
    private var contactNumber: String? = null
    private var mIntent: Intent? = null
    private var number: Int = 0
    private var position: Int = 0
    private lateinit var feedList: GetFeedListData
    private lateinit var tabList: GetFeedTabData

    companion object {
        private const val ARG_TAB_NAME = "tabName"
        fun newInstance(tabName: Int): WaterfallFragment {
            val args = Bundle()
            args.putString(ARG_TAB_NAME, tabName.toString())
            args.putInt(ARG_TAB_NAME, tabName)
            val fragment = WaterfallFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRechargeWaterfallBinding.inflate(inflater, container, false)
        return binding.root

    }

    //用于请求读取联系人权限并执行相应的操作。
    private fun requestReadContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 如果已经有权限，直接执行读取联系人数据的操作
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            pickContactLauncher.launch(intent)
        } else {
            // 请求权限
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    // 处理权限请求结果
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 用户同意了权限，跳转到通讯录界面，选择充值号码
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            pickContactLauncher.launch(intent)
        } else {
            // 用户拒绝了权限，可以给出相应的提示或处理逻辑
            Toast.makeText(context, "请打开读取联系人权限", Toast.LENGTH_SHORT).show()
            //requestReadContactsPermission()
        }
    }

    //使用registerForActivityResult来注册一个用于选择联系人的Activity结果回调
    private val pickContactLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                mIntent = result.data
                val contactUri = mIntent?.data
                contactNumber = getContactNumberByUri(contactUri)
                contactNumber?.let { GetTelephoneNumberManager.triggerGetTelephoneNumber(it) }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(MyFragmentObserver())



    }

    override fun loadData() {
       // binding.tabLayout.selectTab(null) // 取消选中状态
        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String = requireContext().assets.open("getFeedListData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        feedList = gson.fromJson(json, GetFeedListData::class.java)
        myAdapter = WaterfallAdapter(feedList.feedList)
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
        }
        //注册子组件的点击事件
        myAdapter.addChildClickViewIds(R.id.btnSelect)

        //监听条目子组件的点击事件
        myAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.btnSelect) {
                //刷新指定item
                //获取要刷新的position
                Log.d("TAG", position.toString())

                requestReadContactsPermission()


                myAdapter.notifyItemChanged(position)

/*
                val updatedItem = myAdapter.getItem(position)
                if (updatedItem.quickRecharge != null) {
                    updatedItem.quickRecharge.title = contactNumber

                    // 更新适配器中的数据集
                    feedList.feedList[position] = updatedItem // 将索引为1的项替换为更新后的项
                    myAdapter.notifyItemChanged(position)
                }*/

            }
        }

        myAdapter.setOnItemClickListener { _, _, position ->
            Toast.makeText(context, "onItemClick $position", Toast.LENGTH_SHORT).show()
        }

        // 设置回调监听器
        // 在合适的地方触发加载更多事件
        LoadMoreManager.setOnLoadMoreListener(object : LoadMoreManager.OnLoadMoreListener {
            override fun onLoadMore() {
                // 在这里触发加载更多数据的操作
                val data: MutableList<GetFeedListData.FeedListBean> = mutableListOf()
                data.add(
                    feedList.feedList[5]
                )
                data.add(
                    feedList.feedList[6]
                )
                data.add(
                    feedList.feedList[16]
                )
                data.add(
                    feedList.feedList[15]
                )
                myAdapter.addMoreValue(feedList.feedList, data)
                val startPosition = feedList.feedList.size - 2 // 开始位置是已有数据的最后两个位置
                val itemCount = data.size // 添加的数据项数
                myAdapter.notifyItemRangeInserted(startPosition, itemCount)
            }
        })
        val jsonTab: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            requireContext().assets.open("getFeedTabData.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gsonTab = Gson()
        tabList = gsonTab.fromJson(jsonTab, GetFeedTabData::class.java)

        number = requireArguments().getInt(ARG_TAB_NAME)



        setCustomIcon(number)

      /* val tab = binding.tabLayout.newTab()
        val tabView =
            LayoutInflater.from(context).inflate(R.layout.view_fragment_tab, null)
        val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
        val tabName = tabView.findViewById<TextView>(R.id.tabName)

        tabName.text = tabList.tabList[0].tagList[0].tagName
        Glide.with(requireActivity())
            .load(tabList.tabList[0].tagList[0].tagIcon)
            .error(R.drawable.ic_launcher_foreground)
            .into(tabIcon)
        tab.customView = tabView*/

        /*binding.tabLayout.addTab(binding.tabLayout.newTab().setText(tabList.tabList[tabName].tagList[0].tagName))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(tabList.tabList[tabName].tagList[1].tagName))*/
        //binding.tabLayout.addTab(binding.tabLayout.newTab().setIcon(tabList.tabList[0].tabIcon))

        // 刷新TabLayout时判断是否有选中标签

        // 设置标签切换监听
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

           /*     val select = binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)
                if (select != null) {
                    // 有选中标签的处理逻辑
                    // 可以调用 selectedTab.select() 重新触发选中逻辑
                    updateTabFont(tab, true) /
                } else {
                    // 没有选中标签的处理逻辑
                    updateTabFont(tab, false) // 取消选中标签字体加粗
                }*/
                updateTabFont(tab, true) // 设置选中标签字体加粗
                Log.d("WaterfallActivity", tab.position.toString())
                when (tab.position) {
                    0 -> {
                        // 在这里触发加载更多数据的操作
                        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
                        val requireJson: String = requireContext().assets.open("getQueryListData.json").bufferedReader()
                            .use { it.readText() }
                        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
                        val requireGson = Gson()
                        val requireList = requireGson.fromJson(requireJson, GetFeedListData::class.java)
                        myAdapter = WaterfallAdapter(requireList.feedList)
                        binding.rvComponentsWaterfall.apply {
                            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                            adapter = myAdapter
                        }



                    }
                    1 -> {
                        // 在这里触发加载更多数据的操作
                        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
                        val requireJson: String = requireContext().assets.open("getQuery2ListData.json").bufferedReader()
                            .use { it.readText() }
                        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
                        val requireGson = Gson()
                        val requireList = requireGson.fromJson(requireJson, GetFeedListData::class.java)
                        myAdapter = WaterfallAdapter(requireList.feedList)
                        binding.rvComponentsWaterfall.apply {
                            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                            adapter = myAdapter
                        }
                        /*// 在这里触发加载更多数据的操作
                        val data: MutableList<GetFeedListData.FeedListBean> = mutableListOf()
                        data.add(
                            feedList.feedList[7]
                        )
                        data.add(
                            feedList.feedList[8]
                        )
                        data.add(
                            feedList.feedList[17]
                        )
                        data.add(
                            feedList.feedList[18]
                        )
                        myAdapter.refreshValue(feedList.feedList, data)
                        myAdapter.notifyDataSetChanged()*/
                    }

                }

                //myAdapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabFont(tab, false) // 取消选中标签字体加粗
           /*     //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
                binding.rvComponentsWaterfall.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = myAdapter
                }
                //注册子组件的点击事件
                myAdapter.addChildClickViewIds(R.id.btnSelect)

                //监听条目子组件的点击事件
                myAdapter.setOnItemChildClickListener { adapter, view, position ->
                    if (view.id == R.id.btnSelect) {
                        requestReadContactsPermission()
                    }
                }

                myAdapter.setOnItemClickListener { _, _, position ->
                    Toast.makeText(context, "onItemClick $position", Toast.LENGTH_SHORT).show()
                }

                // 设置回调监听器
                // 在合适的地方触发加载更多事件
                LoadMoreManager.setOnLoadMoreListener(object : LoadMoreManager.OnLoadMoreListener {
                    override fun onLoadMore() {
                        // 在这里触发加载更多数据的操作
                        val data: MutableList<GetFeedListData.FeedListBean> = mutableListOf()
                        data.add(
                            feedList.feedList[5]
                        )
                        data.add(
                            feedList.feedList[6]
                        )
                        data.add(
                            feedList.feedList[16]
                        )
                        data.add(
                            feedList.feedList[15]
                        )
                        myAdapter.addMoreValue(feedList.feedList, data)
                        val startPosition = feedList.feedList.size - 2 // 开始位置是已有数据的最后两个位置
                        val itemCount = data.size // 添加的数据项数
                        myAdapter.notifyItemRangeInserted(startPosition, itemCount)
                    }
                })*/
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                // Tab重新选中时的处理逻辑
                binding.tabLayout.selectTab(null) // 取消选中状态


                //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
                val json: String = requireContext().assets.open("getFeedListData.json").bufferedReader()
                    .use { it.readText() }
                //使用了Gson库来将JSON数据转换为GetFeedTabData对象
                val gson = Gson()
                feedList = gson.fromJson(json, GetFeedListData::class.java)
                myAdapter = WaterfallAdapter(feedList.feedList)
                binding.rvComponentsWaterfall.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = myAdapter
                }
            }


        })
    }

    // 辅助方法：更新标签字体样式
    fun updateTabFont(tab: TabLayout.Tab, isSelected: Boolean) {
        val customView: View? = tab.customView
        if (customView != null) {
            val tabName: TextView =
                customView.findViewById(R.id.tabName) as TextView // 自定义布局中的 TextView
            CoroutineScope(Dispatchers.Main).launch {

                for (i in 0 until tabList.tabList[number].tagList.size) {
                    Log.d("tagName", tabList.tabList[number].tagList[i].tagName)
                    if (tabList.tabList[number].tagList[i].tagType == "2"){
                        if (isSelected) {
                            val drawable =
                                ResourcesCompat.getDrawable(resources,R.drawable.shape_fragment_red_tab,null)
                            customView.background = drawable // 设置背景色
                            tabName.setTextColor(Color.parseColor("#ffffff"))
                        } else {
                            val drawable =
                                ResourcesCompat.getDrawable(resources,R.drawable.shape_fragment_tab,null)
                            customView.background = drawable // 设置背景色
                            tabName.setTextColor(Color.parseColor("#f35656"))
                        }
                    } else{
                        if (isSelected) {
                            val drawable =
                                ResourcesCompat.getDrawable(resources,R.drawable.shape_fragment_blue_tab,null)
                            customView.background = drawable // 设置背景色
                            tabName.setTextColor(Color.parseColor("#ffffff"))
                        } else {
                            val drawable =
                                ResourcesCompat.getDrawable(resources,R.drawable.shape_fragment_tab,null)
                            customView.background = drawable // 设置背景色
                            tabName.setTextColor(Color.parseColor("#999999"))
                        }
                    }

                }
            }
        }
    }

    private fun getContactNumberByUri(data: Uri?): String? {

        var phoneNumber: String? = null
        //来获取一个光标（Cursor）对象。这里使用 contentResolver 来查询联系人的数据
        val cursor =
            data?.let { requireActivity().contentResolver.query(it, null, null, null, null) }
        //使用 Kotlin 的 use 函数，确保在使用完光标后关闭它
        cursor?.use { it ->
            //这行代码检查 Cursor 是否有数据，并将 Cursor 定位到第一行
            if (it.moveToFirst()) {
                //这行代码获取存储联系人是否有电话号码的列的索引。
                // HAS_PHONE_NUMBER 是联系人表中的一个列，它表示该联系人是否有电话号码。
                val hasPhoneIndex = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                //这行代码获取联系人 ID 列的索引。_ID 是联系人表中的一个列，它表示每个联系人的唯一标识符。
                val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)

                //这行代码从 Cursor 中获取存储联系人是否有电话号码的值，并将其存储在名为 hasPhone 的变量中。
                var hasPhone = it.getString(hasPhoneIndex)
                val id = it.getString(idIndex)

                // 这行代码将 hasPhone 的值进行比较。如果它等于字符串 "1"（忽略大小写），
                // 则将 hasPhone 设置为 "true"，否则设置为 "false"。
                // 这样做是为了将数据库中存储的 1/0 值转换为更易读的布尔值。
                hasPhone = if (hasPhone.equals("1", ignoreCase = true)) {
                    "true"
                } else {
                    "false"
                }

                if (hasPhone.toBoolean()) {
                    //这行代码使用 contentResolver 查询联系人的电话号码。
                    // requireActivity() 返回与当前 Fragment 相关联的 Activity。
                    val phonesCursor = requireActivity().contentResolver.query(
                        //表示查询电话号码的内容 URI，指定了查询电话号码的数据表。
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        //表示查询条件，限制查询结果必须与给定的联系人 ID 相匹配。
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null
                    )

                    // 这行代码使用了安全调用操作符 ?. 以确保 phonesCursor 不为 null，并在使用后自动关闭 Cursor
                    phonesCursor?.use {
                        //这行代码检查 Cursor 是否有数据，并将 Cursor 定位到第一行
                        if (it.moveToFirst()) {
                            //获取电话号码列的索引，将其赋值给 phoneNumberIndex。
                            val phoneNumberIndex =
                                it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            //通过索引从光标中获取电话号码，并将其赋值给 phoneNumber
                            phoneNumber = it.getString(phoneNumberIndex)

                        }
                    }
                }
            }
        }
        return phoneNumber
    }

    /**
     * 设置自定义位置图标
     */
    private fun setCustomIcon(number :Int) {

        for (i in 0 until tabList.tabList[number].tagList.size) {
            binding.tabLayout.addTab(binding.tabLayout.newTab(),false)
        }
        for (i in 0 until tabList.tabList[number].tagList.size) {
            binding.tabLayout.getTabAt(i)?.customView = makeTabView(i, number)

        }
    }

    /**
     * 引入布局设置图标和标题
     * @param position
     * @return
     */
    private fun makeTabView(position: Int,number: Int): View {
        val tabView: View = LayoutInflater.from(requireContext()).inflate(R.layout.view_fragment_tab, null)
        val textView = tabView.findViewById<TextView>(com.example.myapplication.R.id.tabName)
        val imageView = tabView.findViewById<ImageView>(R.id.tabIcon)
        textView.setText(tabList.tabList[number].tagList[position].tagName)
        if (tabList.tabList[number].tagList[position].tagType == "2"){
            val drawable =
                ResourcesCompat.getDrawable(resources,R.drawable.shape_fragment_tab,null)
            tabView.background = drawable // 设置背景色
            textView.setTextColor(Color.parseColor("#f35656"))
            }
        Glide.with(requireActivity())
            .load(tabList.tabList[number].tagList[position].tagIcon)
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
        //imageView.setImageResource(tabList.tabList[0].tagList[position].tagIcon)
        return tabView
    }
}


