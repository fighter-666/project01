package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.adapter.LocationAdapter
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town
import java.util.Random


class TreeListActivity : AppCompatActivity() {
    private lateinit var mAdapter: LocationAdapter
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_list)
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        initAdapter()

        mAdapter.expandAll()
    }

    private fun initAdapter() {
        val dataList = mockData(10)
        mAdapter = LocationAdapter(dataList)
        mRecyclerView.adapter = mAdapter
    }

    // 模拟数据
    private fun mockData(pageSize: Int): List<MultiItemEntity> {
        val mRandom = Random()
        val provinceList: MutableList<Province> = ArrayList()
        for (i in 0 until pageSize) {
            // 省份
            val province = Province(String.format("Province %s", pageSize + i))
            provinceList.add(province)
            val cityCount: Int = mRandom.nextInt(5)
            for (j in 0 until cityCount) {
                // 城市
                val city = City(String.format("City %s-%s", i, j))
                province.addSubItem(city)
                val townCount: Int = mRandom.nextInt(5)
                for (k in 0 until townCount) {
                    // 乡镇
                    city.addSubItem(Town(String.format("Town %s-%s-%s", i, j, k)))
                }
            }
        }
        return provinceList
    }
}