package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.components.FifthActivity
import com.example.myapplication.components.FourthActivity
import com.example.myapplication.components.SecondActivity
import com.example.myapplication.components.SisthActivity
import com.example.myapplication.components.ThirdActivity
import com.example.myapplication.databinding.FragmentLabBinding
import com.example.myapplication.components.RechargePage
import com.gyf.immersionbar.ImmersionBar

class LabFragment : Fragment(){
    private  var _binding : FragmentLabBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLabBinding.inflate(inflater, container, false)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.toolbar)    //解决状态栏和布局重叠问题，任选其一
            .init();
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //创建了一个包含多个 Piggy 对象的可变列表 piggies，
        // 每个 Piggy 对象都包含了一个图片资源 ID 和一个帮助文本
        val piggies = listOf(
            Pair(R.mipmap.icon_grid_color_helper, "1"),
            Pair(R.mipmap.icon_grid_device_helper, "2"),
            Pair(R.mipmap.icon_grid_drawable_helper, "3"),
            Pair(R.mipmap.icon_grid_tip_dialog, "4"),
            Pair(R.mipmap.icon_grid_view_helper, "5"),
            Pair(R.mipmap.icon_grid_tip_dialog, "6")
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()

        //创建适配器
        val myAdapter = LabAdapter(R.layout.lab, piggies)

        //设置布局管理器和给 recyclerView设置适配器
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdapter
        }


        //设置点击事件监听器
        myAdapter.setOnItemClickListener { piggy ->
            // 处理列表项点击事件
            Toast.makeText(context, piggy.name, Toast.LENGTH_SHORT).show()
            when (piggy.name) {
                "1" -> {
                    val intent = Intent(context, RechargePage::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                "2" -> {
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                "3" -> {
                    val intent = Intent(context, ThirdActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                "4" -> {
                    val intent = Intent(context, FourthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                "5" -> {
                    val intent = Intent(context, FifthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                "6" -> {
                    val intent = Intent(context, SisthActivity::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
                // 其他Piggy对象的处理逻辑...

                else -> {
                    // 默认的页面跳转逻辑
                    val intent = Intent(context, RechargePage::class.java)
                    intent.putExtra("piggyName", piggy.name)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}


