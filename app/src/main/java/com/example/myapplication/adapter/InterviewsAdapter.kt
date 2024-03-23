package com.example.myapplication.adapter

import android.graphics.BitmapFactory
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.databinding.AdapterInterviewsBinding
import com.example.myapplication.recharge.view.property.Piggy

class InterviewsAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) :
    BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = AdapterInterviewsBinding.bind(holder.itemView)

        //设置图片资源为item.image
        val bitmap = BitmapFactory.decodeResource(mContext.resources, item.image)
        binding.ivCustomImageView.setImageBitmap(bitmap)

        //设置文本为item.name
        binding.tvTitle.text = item.name

    }

}