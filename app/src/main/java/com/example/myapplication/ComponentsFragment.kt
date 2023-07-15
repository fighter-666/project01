package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Pair(R.drawable.ic_canyin, "QMUIColorHelper"),
            Pair(R.drawable.ic_canyin_fs, "QMUIDeviceHelper"),
            Pair(R.drawable.ic_fushi, "QWUIDrawableHelper"),
            Pair(R.drawable.ic_gouwu, "QMUIStatusBarHelper"),
            Pair(R.drawable.ic_gouwu_fs, "QMUIViewHelper"),
            Pair(R.drawable.ic_jiaotong, "QMUINotchHelper")
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()
        val myAdapter = ComponentsAdapter(R.layout.components, piggies)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


}


