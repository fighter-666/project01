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
        piggies = ArrayList()
        when{

        }
        for (i in 1 until 7) {
             if (i == 1) { val image1 =R.drawable.ic_canyin
                 piggies.add(Piggy(image1, "QMUIColorHelper"))}
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

//            val image2 = if (i == 2)  R.drawable.ic_canyin_fs
//            val image3 = if (i == 3)  R.drawable.ic_fushi
//            val image4 = if (i == 4)  R.drawable.ic_gouwu
//            val image5 = if (i == 5)  R.drawable.ic_gouwu_fs
//            val image6 = if (i == 6) R.drawable.bgs else R.drawable.ic_jiaotong
//            piggies.add(Piggy(image1, "QMUIColorHelper"))
//            piggies.add(Piggy(image2, "QMUIDeviceHelper"))
//            piggies.add(Piggy(image3, "QWUIDrawableHelper"))
//            piggies.add(Piggy(image4, "QMUIStatusBarHelper"))
//            piggies.add(Piggy(image5, "QMUIViewHelper"))
//            piggies.add(Piggy(image6, "QMUINotchHelper"))
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
         inner class MyViewHolder(binding: ComponentsBinding): RecyclerView.ViewHolder(binding.root) {
        }

         override fun convert(holder: BaseViewHolder, item: Piggy) {
             val binding = ComponentsBinding.bind(holder.itemView)
             binding.itemImage.setImageResource(item.image)
             binding.itemTitle.text = item.name
         }

    }



}


