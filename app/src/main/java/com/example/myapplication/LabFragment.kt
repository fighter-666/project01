package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentLabBinding

class LabFragment : Fragment(){
    private  var _binding : FragmentLabBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val piggies = listOf(
            Pair(R.mipmap.icon_grid_color_helper, "QMUIColorHelper3"),
            Pair(R.mipmap.icon_grid_device_helper, "QMUIDeviceHelper"),
            Pair(R.mipmap.icon_grid_drawable_helper, "QWUIDrawableHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUIStatusBarHelper"),
            Pair(R.mipmap.icon_grid_view_helper, "QMUIViewHelper"),
            Pair(R.mipmap.icon_grid_tip_dialog, "QMUINotchHelper")
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()
        val myAdapter = LabAdapter(R.layout.components, piggies)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdapter
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}


