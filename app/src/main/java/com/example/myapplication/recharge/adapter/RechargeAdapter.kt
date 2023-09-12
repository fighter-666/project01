package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.AdapterHelperBinding
import com.example.myapplication.databinding.AdapterRechargeBinding
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.view.property.Piggy

class RechargeAdapter(@LayoutRes layoutResId: Int, data: MutableList<GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean>) :
    BaseQuickAdapter<GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(
        holder: BaseViewHolder,
        item: GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean,
    ) {
        val binding = AdapterRechargeBinding.bind(holder.itemView)
        binding.tvMainTitle.text = item.mainTitle
        binding.tvSubtitle.text = item.subtitle
    }
}