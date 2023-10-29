package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.activity.components.BannerActivity
import com.example.myapplication.activity.components.BroadcastReceiverActivity
import com.example.myapplication.activity.components.VariousTextviewActivity
import com.example.myapplication.activity.components.CustomActivity
import com.example.myapplication.activity.components.OkhttpActivity
import com.example.myapplication.activity.components.PhoneActivity
import com.example.myapplication.activity.components.ThirdActivity
import com.example.myapplication.activity.components.bilibili.activity.DataBindingActivity
import com.example.myapplication.activity.components.bilibili.activity.LiveDataActivity
import com.example.myapplication.activity.components.bilibili.activity.ScoreActivity
import com.example.myapplication.activity.components.bilibili.activity.CommonControlActivity
import com.example.myapplication.activity.components.bilibili.activity.SharedPreferencesActivity
import com.example.myapplication.activity.components.bilibili.activity.ViewModelTestActivity
import com.example.myapplication.adapter.ComponentsAdapter
import com.example.myapplication.activity.components.WebViewActivity
import com.example.myapplication.activity.components.RechargePageActivity
import com.example.myapplication.activity.components.ServiceActivity
import com.example.myapplication.activity.components.bilibili.activity.Room2Activity
import com.example.myapplication.activity.components.bilibili.activity.RoomActivity
import com.example.myapplication.databinding.FragmentComponentsBinding
import com.example.myapplication.feed.activity.FeedStreamHomePageActivity
import com.example.myapplication.recharge.view.property.Piggy
import com.gyf.immersionbar.ImmersionBar


class ComponentsFragment : Fragment() {
    private var _binding: FragmentComponentsBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentComponentsBinding.inflate(inflater, container, false)
        val view = binding.root

        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.toolbar)    //解决状态栏和布局重叠问题，任选其一
            .init();
        return view

    }

    /*  companion object {
          fun newInstance(text: String): ComponentsFragment {
              val args = Bundle()
              args.putString("text", text)
              val fragment = ComponentsFragment()
              fragment.arguments = args
              return fragment
          }
      }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //创建了一个包含多个 Piggy 对象的可变列表 piggies，
        // 每个 Piggy 对象都包含了一个图片资源 ID 和一个帮助文本
        val piggies = listOf(
            Pair(R.mipmap.icon_grid_color_helper, "RechargePageActivity"),
            Pair(R.mipmap.icon_grid_device_helper, "VariousTextviewActivity"),
            Pair(R.mipmap.icon_grid_drawable_helper, "QWUIDrawableHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "FeedStreamHomePageActivity"),
            Pair(R.mipmap.icon_grid_view_helper, "WebViewActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "CustomActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "CommonControlActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "ViewModelTestActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "LiveDataActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "DataBindingActivity"),
            Pair(R.mipmap.icon_grid_view_helper, "ScoreActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "SharedPreferencesActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "PhoneActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "BannerActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "RoomActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "Room2Activity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "OkhttpActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "ServiceActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "BroadcastReceiverActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "66"),
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()

        //创建适配器
        val myAdapter = ComponentsAdapter(R.layout.adapter_components, piggies)

        //设置布局管理器和给recyclerView设置适配器
        binding.rvComponents.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter
        }

        myAdapter.setOnItemClickListener { _, view, position ->
            Toast.makeText(context, "onItemClick $position", Toast.LENGTH_SHORT).show()
            when (position) {
                0-> {
                    val intent = Intent(context, RechargePageActivity::class.java)
                    startActivity(intent)
                }

                1-> {
                    val intent = Intent(context, VariousTextviewActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(context, ThirdActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(context, FeedStreamHomePageActivity::class.java)
                    startActivity(intent)
                }

                4 -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(context, CustomActivity::class.java)
                    startActivity(intent)
                }

                6 -> {
                    val intent = Intent(context, CommonControlActivity::class.java)
                    startActivity(intent)
                }

                7 -> {
                    val intent = Intent(context, ViewModelTestActivity::class.java)
                    startActivity(intent)
                }

                8 -> {
                    val intent = Intent(context, LiveDataActivity::class.java)
                    startActivity(intent)
                }

                9-> {
                    val intent = Intent(context, DataBindingActivity::class.java)
                    startActivity(intent)
                }

                10 -> {
                    val intent = Intent(context, ScoreActivity::class.java)
                    startActivity(intent)
                }

                11 -> {
                    val intent = Intent(context, SharedPreferencesActivity::class.java)
                    startActivity(intent)
                }

               12 -> {
                    val intent = Intent(context, PhoneActivity::class.java)
                    startActivity(intent)
                }

                13 -> {
                    val intent = Intent(context, BannerActivity::class.java)
                    startActivity(intent)
                }

                14 -> {
                    val intent = Intent(context, RoomActivity::class.java)
                    startActivity(intent)
                }

                15 -> {
                    val intent = Intent(context, Room2Activity::class.java)
                    startActivity(intent)
                }
                16 -> {
                    val intent = Intent(context, OkhttpActivity::class.java)
                    startActivity(intent)
                }
                17 -> {
                    val intent = Intent(context, ServiceActivity::class.java)
                    startActivity(intent)
                }
                18 -> {
                    val intent = Intent(context, BroadcastReceiverActivity::class.java)
                    startActivity(intent)
                }
                // 其他Piggy对象的处理逻辑...

                else -> {
                    // 默认的页面跳转逻辑
                    val intent = Intent(context, RechargePageActivity::class.java)
                    startActivity(intent)
                }
            }
        }
       /* myAdapter.setOnItemClickListener { piggy ->
            // 处理列表项点击事件
            Toast.makeText(context, piggy.name, Toast.LENGTH_SHORT).show()
            when (piggy.name) {
                "RechargePageActivity" -> {
                    val intent = Intent(context, RechargePageActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "VariousTextviewActivity" -> {
                    val intent = Intent(context, VariousTextviewActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QWUIDrawableHelper" -> {
                    val intent = Intent(context, ThirdActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "FeedStreamHomePageActivity" -> {
                    val intent = Intent(context, FeedStreamHomePageActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "WebViewActivity" -> {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "CustomActivity" -> {
                    val intent = Intent(context, CustomActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "CommonControlActivity" -> {
                    val intent = Intent(context, CommonControlActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "ViewModelTestActivity" -> {
                    val intent = Intent(context, ViewModelTestActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "LiveDataActivity" -> {
                    val intent = Intent(context, LiveDataActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "DataBindingActivity" -> {
                    val intent = Intent(context, DataBindingActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "ScoreActivity" -> {
                    val intent = Intent(context, ScoreActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "SharedPreferencesActivity" -> {
                    val intent = Intent(context, SharedPreferencesActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "PhoneActivity" -> {
                    val intent = Intent(context, PhoneActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "BannerActivity" -> {
                    val intent = Intent(context, BannerActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "RoomActivity" -> {
                    val intent = Intent(context, RoomActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "Room2Activity" -> {
                    val intent = Intent(context, Room2Activity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                // 其他Piggy对象的处理逻辑...

                else -> {
                    // 默认的页面跳转逻辑
                    val intent = Intent(context, RechargePageActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
            }
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


