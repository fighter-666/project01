package com.example.myapplication

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.ComponentsBinding

class ComponentsAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    private var onItemClickListener: ((Piggy) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = ComponentsBinding.bind(holder.itemView)

        //设置图片和标题
        binding.itemImage.setImageResource(item.image)
        binding.itemTitle.text = item.name

        //设置点击事件监听器
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    fun setOnItemClickListener(listener: (Piggy) -> Unit) {
        onItemClickListener = listener
    }


}