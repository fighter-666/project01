package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.activity.AssetsActivity
import com.example.myapplication.activity.BaseApplication
import com.example.myapplication.activity.DataActivity
import com.example.myapplication.activity.DataReadActivity
import com.example.myapplication.activity.InterviewsActivity
import com.example.myapplication.activity.RawActivity
import com.example.myapplication.activity.SdcardActivity
import com.example.myapplication.activity.TreeListActivity
import com.example.myapplication.activity.components.AdvertisingActivity
import com.example.myapplication.activity.components.AsyncServiceActivity
import com.example.myapplication.activity.components.BallViewActivity
import com.example.myapplication.activity.components.BannerActivity
import com.example.myapplication.activity.components.BroadcastReceiverActivity
import com.example.myapplication.activity.components.VariousTextviewActivity
import com.example.myapplication.activity.components.CustomActivity
import com.example.myapplication.activity.components.DataUsageActivity
import com.example.myapplication.activity.components.FirstRoomActivity
import com.example.myapplication.activity.components.HotListActivity
import com.example.myapplication.activity.components.NotificationActivity
import com.example.myapplication.activity.components.NotifyActivity
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
import com.example.myapplication.activity.components.RecyclerViewActivity
import com.example.myapplication.activity.components.SQLiteActivity
import com.example.myapplication.activity.components.ServiceActivity
import com.example.myapplication.activity.components.StudentActivity
import com.example.myapplication.activity.components.ThreadsActivity
import com.example.myapplication.activity.components.UserActivity
import com.example.myapplication.activity.components.ViewpageActivity
import com.example.myapplication.activity.WebViewX5Activity
import com.example.myapplication.activity.components.AudioTrackActivity
import com.example.myapplication.activity.components.bilibili.activity.Room2Activity
import com.example.myapplication.activity.components.bilibili.activity.RoomActivity
import com.example.myapplication.call.CallActivity
import com.example.myapplication.databinding.FragmentComponentsBinding
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
            .init()
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
            Pair(R.mipmap.icon_grid_tip_dialog, "AdvertisingActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "AsyncServiceActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "StudentActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "UserActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "BaseApplicationActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "FirstRoomActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "HotListActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "ViewpageActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "TreeListActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "DataUsageActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "SQLiteActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "RecyclerViewActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "InterviewsActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "BallViewActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "NotifyActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "NotificationActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "ThreadsActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "CallActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "WebViewX5Activity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "AudioTrackActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "RawActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "AssetsActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "DataActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "DataReadActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "SdcardActivity"),
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
                0 -> {
                    val intent = Intent(context, RechargePageActivity::class.java)
                    startActivity(intent)
                }

                1 -> {
                    val intent = Intent(context, VariousTextviewActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(context, ThirdActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(context, ThirdActivity::class.java)
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

                9 -> {
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

                19 -> {
                    val intent = Intent(context, AdvertisingActivity::class.java)
                    startActivity(intent)
                }
                20 -> {
                    val intent = Intent(context, AsyncServiceActivity::class.java)
                    startActivity(intent)
                }
                21 -> {
                    val intent = Intent(context, StudentActivity::class.java)
                    startActivity(intent)
                }
                22 -> {
                    val intent = Intent(context, UserActivity::class.java)
                    startActivity(intent)
                }
                23 -> {
                    val intent = Intent(context, BaseApplication::class.java)
                    startActivity(intent)
                }
                24 -> {
                    val intent = Intent(context, FirstRoomActivity::class.java)
                    startActivity(intent)
                }
                25 -> {
                    val intent = Intent(context, HotListActivity::class.java)
                    startActivity(intent)
                }
                26 -> {
                    val intent = Intent(context, ViewpageActivity::class.java)
                    startActivity(intent)
                }
                27 -> {
                    val intent = Intent(context, TreeListActivity::class.java)
                    startActivity(intent)
                }
                28 -> {
                    val intent = Intent(context, DataUsageActivity::class.java)
                    startActivity(intent)
                }
                29 -> {
                    val intent = Intent(context, SQLiteActivity::class.java)
                    startActivity(intent)
                }
                30 -> {
                    val intent = Intent(context, RecyclerViewActivity::class.java)
                    startActivity(intent)
                }
                31 -> {
                    val intent = Intent(context, InterviewsActivity::class.java)
                    startActivity(intent)
                }
                32 -> {
                    val intent = Intent(context, BallViewActivity::class.java)
                    startActivity(intent)
                }
                33 -> {
                    val intent = Intent(context, NotifyActivity::class.java)
                    startActivity(intent)
                }
                34 -> {
                    val intent = Intent(context, NotificationActivity::class.java)
                    startActivity(intent)
                }
                35 -> {
                    val intent = Intent(context, ThreadsActivity::class.java)
                    startActivity(intent)
                }
                36 -> {
                    val intent = Intent(context, CallActivity::class.java)
                    startActivity(intent)
                }
                37 -> {
                    val intent = Intent(context, WebViewX5Activity::class.java)
                    startActivity(intent)
                }
                38 -> {
                    val intent = Intent(context, AudioTrackActivity::class.java)
                    startActivity(intent)
                }
                39 -> {
                    val intent = Intent(context, RawActivity::class.java)
                    startActivity(intent)
                }
                40 -> {
                    val intent = Intent(context, AssetsActivity::class.java)
                    startActivity(intent)
                }
                41 -> {
                    val intent = Intent(context, DataActivity::class.java)
                    startActivity(intent)
                }
                42 -> {
                    val intent = Intent(context, DataReadActivity::class.java)
                    startActivity(intent)
                }
                43 -> {
                    val intent = Intent(context, SdcardActivity::class.java)
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


