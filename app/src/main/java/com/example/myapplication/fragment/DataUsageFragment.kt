package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.adapter.LocationAdapter
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town
import com.example.myapplication.data.UserFluxPackageData
import com.example.myapplication.databinding.FragmentDataUsageBinding
import com.example.myapplication.widget.BaseLazyFragment
import com.google.gson.Gson
import java.util.Random


class DataUsageFragment : BaseLazyFragment() {
    private var _binding: FragmentDataUsageBinding? = null
    val binding get() = _binding!!
    private lateinit var mAdapter: TestAdapter
    private lateinit var mAdapter2: LocationAdapter
    private val multiItemEntities = mutableListOf<MultiItemEntity>()

    companion object {
        private const val ARG_TAB_NAME = "tabName"
        fun newInstance(tabName: Int): DataUsageFragment {
            val args = Bundle()
            args.putString(ARG_TAB_NAME, tabName.toString())
            args.putInt(ARG_TAB_NAME, tabName)
            val fragment = DataUsageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDataUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun loadData() {


       /* feedList = gson.fromJson(json, GetFeedListData::class.java)
        myAdapter = WaterfallAdapter(feedList.feedList)
        mRecyclerView = findViewById(R.id.recyclerView)*/
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(context)
        }
        initAdapter()

        //mAdapter.expandAll()

        }

    private fun initAdapter() {
        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String = requireContext().assets.open("getUserFluxPackageData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val fluxPackage = gson.fromJson(json, UserFluxPackageData::class.java)
        Log.d("fluxPackageList", fluxPackage.productOFFRatable.ratableResourcePackages[0].title.toString())


        mAdapter = TestAdapter(multiItemEntities)
        generateData(fluxPackage.productOFFRatable.ratableResourcePackages)
        binding.recyclerView.adapter = mAdapter


        val dataList = mockData(1)
        mAdapter2 = LocationAdapter(dataList)

        binding.recyclerView2.adapter = mAdapter2
    }

    // 模拟数据
    private fun mockData(pageSize: Int): List<MultiItemEntity> {
        val mRandom = Random()
        val provinceList: MutableList<Province> = ArrayList()

        // 生成指定数量的省份数据
        for (i in 0 until pageSize) {
            // 创建一个省份对象
            val province = Province(String.format("Province %s", pageSize + i))
            provinceList.add(province)

            // 生成该省份下的随机数量的城市数据
            val cityCount: Int = mRandom.nextInt(5)
            for (j in 0 until cityCount) {
                // 创建一个城市对象
                val city = City(String.format("City %s-%s", i, j))
                // 将城市对象添加到省份的子项列表中
                province.addSubItem(city)

                // 生成该城市下的随机数量的乡镇数据
                val townCount: Int = mRandom.nextInt(5)
                for (k in 0 until townCount) {
                    // 创建一个乡镇对象
                    val town = Town(String.format("Town %s-%s-%s", i, j, k))
                    // 将乡镇对象添加到城市的子项列表中
                    city.addSubItem(town)
                }
            }
        }

        // 返回生成的省份列表
        return provinceList
    }

    private fun generateData(item: List<UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean>) {
        val lv0Count = item.size
        for (i in 0 until lv0Count) {
            val lv0 = item[i]
            val lv1Count = lv0.productInfos.size
            for (j in 0 until lv1Count) {
                val lv1 = lv0.productInfos[j]
                lv0.addSubItem(lv1)
            }
            multiItemEntities.add(lv0)
        }

        if (multiItemEntities.size > 0) {
            //llMealNormal.visibility = View.VISIBLE
        }
        mAdapter.notifyDataSetChanged()
    }

}


