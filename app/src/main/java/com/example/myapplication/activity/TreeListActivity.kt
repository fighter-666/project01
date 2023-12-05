package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.adapter.LocationAdapter
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town
import com.example.myapplication.extend.times
import com.example.myapplication.util.Money
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

        val money1 =Money(5)
        val money2 =Money(10)
        val moeny3= money1+money2
        val moeny4= money1+20
        Log.d("money",moeny3.value.toString())
        Log.d("money",moeny4.value.toString())

        fun getRandomLengthString(str:String):String{
            val n = (1..20).random()
            val builder = StringBuilder()
            repeat(n){
                builder.append(str)
            }
            return builder.toString()
        }

        val str ="abc" * 3
        Log.d("money",str)


    }
 /*   fun num1AndNum2(num1:Int,num2:Int,operation(Int,Int)->Int):Int{
        val result = operation(num1,num2)
        return result
    }*/

    fun plus(num1:Int,num2:Int):Int{
        return num1+num2
    }
    fun minus(num1:Int,num2:Int):Int{
        return num1 - num2
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