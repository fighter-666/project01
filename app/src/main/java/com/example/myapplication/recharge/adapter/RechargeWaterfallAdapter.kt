package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.AdapterRechargeTabWaterfallBinding
import com.example.myapplication.databinding.AdapterRechargeWaterfallBinding
import com.example.myapplication.recharge.view.property.Card
import com.example.myapplication.recharge.property.Cards
import com.example.myapplication.util.DensityUtils

class RechargeWaterfallAdapter(@LayoutRes layoutResId: Int, data: MutableList<Cards>) :
    BaseQuickAdapter<Cards, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Cards) {
        //将 holder.itemView（列表项的根视图）与生成的绑定类 WaterfallBinding 进行绑定。
        // 通过调用 bind() 方法，你可以获取到生成的绑定类的实例 binding。
        val binding = AdapterRechargeTabWaterfallBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name2.setBackgroundResource(item.backGround)
        binding.name3.setBackgroundResource(item.backGround2)
        binding.name.text = item.name
        binding.name2.text = item.name2
        binding.name3.text = item.name3
        binding.name4.text = item.name4
        binding.name5.text = item.name5
        binding.name6.text = item.name6

        //判断显示与掩藏
        if (item.name2 != "0") {
            binding.name2.setText(item.name2)
            binding.name2.isGone = false
        } else {
            binding.name2.isGone = true
        }

        if (item.name3 != "0") {
            binding.name3.setText(item.name3)
            binding.name3.isGone = false
        } else {
            binding.name3.isGone = true
        }

        if (item.name4 != "0") {
            binding.name4.setText(item.name4)
            binding.name4.isGone = false
        } else {
            binding.name4.isGone = true
        }

        if (item.name5 != "0") {
            binding.name5.setText(item.name5)
            binding.name5.isGone = false
        } else {
            binding.name5.isGone = true
        }

        if (item.name6 != "0") {
            binding.name6.setText(item.name6)
            binding.name6.isGone = false
        } else {
            binding.name6.isGone = true
        }
        //setCardView(holder, binding, item)
        /*val layoutParams2 = holder.itemView.layoutParams
        val layoutParams = binding.ivImage.layoutParams
        layoutParams.height = layoutParams2.width
        binding.ivImage.layoutParams = layoutParams*/
    }



}