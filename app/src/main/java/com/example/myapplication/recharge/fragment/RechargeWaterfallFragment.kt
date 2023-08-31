package com.example.myapplication.recharge.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.recharge.adapter.RechargeWaterfallAdapter
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.recharge.adapter.RechargeWaterfallMultipleItemQuickAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.data.GetFeedTabData
import com.example.myapplication.recharge.property.Cards
import com.google.gson.Gson


class RechargeWaterfallFragment : Fragment() {
    private var _binding: FragmentRechargeWaterfallBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRechargeWaterfallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val piggies4 = mutableListOf<Cards>()
        piggies4.add(
            Cards(
                0, 0, R.drawable.falls1, "电信关爱版-为老年人架桥", "0", "0", "0", "0", "0", 0, 0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                R.drawable.shape_rectangle18,
                R.drawable.falls8,
                "加装【副卡】，一份套餐全家用 ",
                "赠新人礼包",
                "赠美团神券",
                "￥",
                "10",
                "/月",
                0,
                0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                R.drawable.shape_rectangle18,
                R.drawable.fall,
                "iPhone12 128GB 红色 双卡双待",
                "免运费",
                "送配件",
                "0",
                "0",
                "0",
                0,
                0
            )
        )
        piggies4.add(
            Cards(
                R.drawable.shape_rectangle18,
                0,
                R.drawable.falls4,
                "15GB定向流量+腾讯视频月会员卡",
                "0",
                "0",
                "0",
                "0",
                "0",
                0,
                0
            )
        )

        val json: String = requireContext().assets.open("waterfalldata.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val feedList= gson.fromJson(json, GetFeedListData::class.java)
        val tag = "TAG"
        for (feed in feedList.feedList) {
            Log.d(tag,"标签名称: ${feed.type}")
        }

        val myAdapter = RechargeWaterfallMultipleItemQuickAdapter( feedList.feedList)
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


