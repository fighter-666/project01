package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.components.FifthActivity
import com.example.myapplication.components.FourthActivity
import com.example.myapplication.components.SecondActivity
import com.example.myapplication.components.SisthActivity
import com.example.myapplication.components.ThirdActivity
import com.example.myapplication.databinding.ComponentsFragmentBinding
import com.example.myapplication.components.RechargePage
import com.gyf.immersionbar.ImmersionBar


class ComponentsFragment : Fragment(){
    private  var _binding : ComponentsFragmentBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ComponentsFragmentBinding.inflate(inflater, container, false)
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
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUINotchHelper")
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()

        //创建适配器
        val myAdapter = ComponentsAdapter(R.layout.components, piggies)

        //设置布局管理器和给recyclerView设置适配器
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter
        }

        myAdapter.setOnItemClickListener { piggy ->
            // 处理列表项点击事件
            Toast.makeText(context, piggy.name, Toast.LENGTH_SHORT).show()
            when (piggy.name) {
                "QMUIColorHelper" -> {
                    val intent = Intent(context, RechargePage::class.java)
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


