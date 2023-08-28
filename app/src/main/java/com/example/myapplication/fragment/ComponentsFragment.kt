package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.property.Piggy
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.activity.ViewModelTestActivity
import com.example.myapplication.activity.components.bilibili.activity.LiveDataActivity
import com.example.myapplication.adapter.ComponentsAdapter
import com.example.myapplication.components.FifthActivity
import com.example.myapplication.components.FourthActivity
import com.example.myapplication.activity.components.SecondActivity
import com.example.myapplication.activity.components.bilibili.activity.ServeActivity
import com.example.myapplication.activity.components.SisthActivity
import com.example.myapplication.activity.components.ThirdActivity
import com.example.myapplication.activity.components.bilibili.activity.DataBindingActivity
import com.example.myapplication.activity.components.bilibili.activity.ScoreActivity
import com.example.myapplication.activity.components.bilibili.activity.SharedPreferencesActivity
import com.example.myapplication.databinding.FragmentComponentsBinding
import com.example.myapplication.components.RechargePageActivity
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
            Pair(R.mipmap.icon_grid_color_helper, "QMUIColorHelper"),
            Pair(R.mipmap.icon_grid_device_helper, "QMUIDeviceHelper"),
            Pair(R.mipmap.icon_grid_drawable_helper, "QWUIDrawableHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUIStatusBarHelper"),
            Pair(R.mipmap.icon_grid_view_helper, "QMUIViewHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUINotchHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "7"),
            Pair(R.mipmap.icon_grid_tip_dialog, "8"),
            Pair(R.mipmap.icon_grid_tip_dialog, "9"),
            Pair(R.mipmap.icon_grid_tip_dialog, "10"),
            Pair(R.mipmap.icon_grid_view_helper, "ScoreActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "SharedPreferencesActivity"),
            Pair(R.mipmap.icon_grid_tip_dialog, "13"),
            Pair(R.mipmap.icon_grid_tip_dialog, "14"),
            Pair(R.mipmap.icon_grid_tip_dialog, "15"),
            Pair(R.mipmap.icon_grid_tip_dialog, "16"),
            Pair(R.mipmap.icon_grid_tip_dialog, "17"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "18"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
            Pair(R.mipmap.icon_grid_tip_dialog, "19"),
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

        myAdapter.setOnItemClickListener { piggy ->
            // 处理列表项点击事件
            Toast.makeText(context, piggy.name, Toast.LENGTH_SHORT).show()
            when (piggy.name) {
                "QMUIColorHelper" -> {
                    val intent = Intent(context, RechargePageActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QMUIDeviceHelper" -> {
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QWUIDrawableHelper" -> {
                    val intent = Intent(context, ThirdActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QMUIStatusBarHelper" -> {
                    val intent = Intent(context, FourthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QMUIViewHelper" -> {
                    val intent = Intent(context, FifthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "QMUINotchHelper" -> {
                    val intent = Intent(context, SisthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "7" -> {
                    val intent = Intent(context, ServeActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "8" -> {
                    val intent = Intent(context, ViewModelTestActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "9" -> {
                    val intent = Intent(context, LiveDataActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "10" -> {
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

                "13" -> {
                    val intent = Intent(context, DataBindingActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "14" -> {
                    val intent = Intent(context, ScoreActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "15" -> {
                    val intent = Intent(context, ScoreActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }

                "16" -> {
                    val intent = Intent(context, ScoreActivity::class.java)
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
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


