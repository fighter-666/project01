package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.ComponentsBinding
import com.example.myapplication.databinding.ComponentsFragmentBinding
import com.example.myapplication.databinding.FragmentLabBinding
import com.example.myapplication.databinding.LabBinding

class LabFragment : Fragment(){
    private  var _binding : FragmentLabBinding? = null
    val binding get() = _binding!!
    private lateinit var piggies: MutableList<Piggy>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLabBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): LabFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = LabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val piggies = listOf(
            Pair(R.drawable.ic_canyin, "QMUIColorHelper3"),
            Pair(R.drawable.ic_canyin_fs, "QMUIDeviceHelper"),
            Pair(R.drawable.ic_fushi, "QWUIDrawableHelper"),
            Pair(R.drawable.ic_gouwu, "QMUIStatusBarHelper"),
            Pair(R.drawable.ic_gouwu_fs, "QMUIViewHelper"),
            Pair(R.drawable.ic_jiaotong, "QMUINotchHelper")
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()
        val myAdapter = MyAdapter(R.layout.components, piggies)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

     class MyAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
         inner class MyViewHolder(binding: LabBinding): RecyclerView.ViewHolder(binding.root) {
        }
         override fun convert(holder: BaseViewHolder, item: Piggy) {
             val binding = ComponentsBinding.bind(holder.itemView)
             binding.itemImage.setImageResource(item.image)
             binding.itemTitle.text = item.name
         }
    }
}


