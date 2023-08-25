package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.SecondBinding
import com.example.myapplication.recharge.property.Second


class SecondAdapter(@LayoutRes layoutResId: Int, data: MutableList<Second>?) : BaseQuickAdapter<Second, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Second) {
        val binding = SecondBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name.text = item.name
        binding.name2.text = item.name2
        binding.name3.setBackgroundResource(item.backGround)
    }
}