package com.example.myapplication

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.ComponentsBinding
import com.example.myapplication.databinding.HelperBinding

class HelperAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    private var onItemClickListener: ((Piggy) -> Unit)? = null
    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = HelperBinding.bind(holder.itemView)
        binding.itemImage.setImageResource(item.image)
        binding.itemTitle.text = item.name

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    fun setOnItemClickListener(listener: (Piggy) -> Unit) {
        onItemClickListener = listener
    }
}