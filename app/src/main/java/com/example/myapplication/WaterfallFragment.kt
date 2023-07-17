package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.components.FifthActivity
import com.example.myapplication.components.FirstActivity
import com.example.myapplication.components.FourthActivity
import com.example.myapplication.components.SecondActivity
import com.example.myapplication.components.SisthActivity
import com.example.myapplication.components.ThirdActivity
import com.example.myapplication.databinding.WaterfallFragmentBinding


class WaterfallFragment : Fragment(){
    private  var _binding : WaterfallFragmentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = WaterfallFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): WaterfallFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = WaterfallFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val piggies = listOf(
            Pair(R.mipmap.icon_grid_color_helper, "QMUIColorHelper"),
            Pair(R.mipmap.icon_grid_device_helper, "QMUIDeviceHelper"),
            Pair(R.mipmap.icon_grid_drawable_helper, "QWUIDrawableHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUIStatusBarHelper"),
            Pair(R.mipmap.icon_grid_view_helper, "QMUIViewHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUINotchHelper")
        ).map { (imageResId, helperText) ->
            Card(imageResId, helperText,0,0)
        }.toMutableList()
        val myAdapter = ComponentsAdapter(R.layout.components, piggies)
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            adapter = myAdapter

        }

        myAdapter.setOnItemClickListener { piggy ->
            // 处理列表项点击事件
            Toast.makeText(context, piggy.name, Toast.LENGTH_SHORT).show()
            when (piggy.name) {
                "QMUIColorHelper" -> {
                    val intent = Intent(context, FirstActivity::class.java)
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
                    val intent = Intent(context, FirstActivity::class.java)
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


