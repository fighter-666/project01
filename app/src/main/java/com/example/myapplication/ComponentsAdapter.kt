package com.example.myapplication

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.ComponentsBinding
import com.example.myapplication.databinding.HelperBinding

class ComponentsAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    inner class MyViewHolder(binding: ComponentsBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = ComponentsBinding.bind(holder.itemView)
        binding.itemImage.setImageResource(item.image)
        binding.itemTitle.text = item.name
    }
}