package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ComponentsFragmentBinding


class ComponentsFragment : Fragment(){
    private  var _binding : ComponentsFragmentBinding? = null
    val binding get() = _binding!!
    private lateinit var piggies: MutableList<Piggy>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ComponentsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): ComponentsFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = ComponentsFragment()
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
            Piggy(imageResId, helperText)
        }.toMutableList()
        val myAdapter = ComponentsAdapter(R.layout.components, piggies)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter

        }
        val adapter = ComponentsAdapter(R.layout.components,piggies)
        adapter.setOnItemClickListener(object : ComponentsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // 处理点击事件，根据 position 获取点击的项在列表中的位置
                val clickedItem = piggies[position]

                // 执行详细的点击事件处理逻辑
                // 例如，根据数据执行特定操作、跳转到详情页等
                Toast.makeText(context, "点击了第 ${position + 1} 项", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


}


