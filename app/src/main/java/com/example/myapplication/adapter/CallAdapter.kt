package com.example.myapplication.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.call.RecentCall
import com.example.myapplication.databinding.AdapterCallBinding

class CallAdapter(@LayoutRes layoutResId: Int, data: List<RecentCall>) :
    BaseQuickAdapter<RecentCall, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: RecentCall) {

        //将 holder.itemView（列表项的根视图）与生成的绑定类 HelperBinding 进行绑定。
        // 通过调用 bind() 方法，你可以获取到生成的绑定类的实例 binding。
        val binding = AdapterCallBinding.bind(holder.itemView)

        //设置图片资源为item.image

        //设置文本为item.name
        binding.tvTitle.text = item.id
        binding.tvSubTitle.text = item.id

    }

}