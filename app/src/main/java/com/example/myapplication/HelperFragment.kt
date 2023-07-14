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
import com.example.myapplication.databinding.FragmentHelperBinding
import com.example.myapplication.databinding.HelperBinding

class HelperFragment : Fragment(){
    private  var _binding : FragmentHelperBinding? = null
    val binding get() = _binding!!
    private lateinit var piggies: MutableList<Piggy>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHelperBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): HelperFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = HelperFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        piggies = ArrayList()
        for (i in 1 until 7) {
             if (i == 1) { val image1 =R.drawable.ic_canyin
                 piggies.add(Piggy(image1, "QMUIColorHelper2"))}
            if (i == 2) { val image1 =R.drawable.ic_canyin_fs
                piggies.add(Piggy(image1, "QMUIDeviceHelper"))}
            if (i == 3) { val image1 =R.drawable.ic_fushi
                piggies.add(Piggy(image1, "QWUIDrawableHelper"))}
            if (i == 4) { val image1 =R.drawable.ic_gouwu
                piggies.add(Piggy(image1, "QMUIStatusBarHelper"))}
            if (i == 5) { val image1 =R.drawable.ic_gouwu_fs
                piggies.add(Piggy(image1, "QMUIViewHelper"))}
            if (i == 6) { val image1 =R.drawable.ic_jiaotong
                piggies.add(Piggy(image1, "QMUINotchHelper"))}
        }

        val myAdapter = MyAdapter(R.layout.components, piggies)
        binding.recyclerView.layoutManager = GridLayoutManager(context,3)
        binding.recyclerView.adapter = myAdapter


        myAdapter.setNewData(piggies)
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

     class MyAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
         inner class MyViewHolder(binding: HelperBinding): RecyclerView.ViewHolder(binding.root) {
        }

         override fun convert(holder: BaseViewHolder, item: Piggy) {
             val binding = ComponentsBinding.bind(holder.itemView)
             binding.itemImage.setImageResource(item.image)
             binding.itemTitle.text = item.name
         }

    }



}


