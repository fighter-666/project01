package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.AdapterHelperBinding
import com.example.myapplication.recharge.view.property.Piggy
import com.example.myapplication.recharge.data.GetFeedListData

class ContentAreaListPicListAdapter(@LayoutRes layoutResId: Int, data: MutableList<GetFeedListData.FeedListBean.ContentAreaListBean>) :
    BaseQuickAdapter<GetFeedListData.FeedListBean.ContentAreaListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(
        holder: BaseViewHolder,
        item: GetFeedListData.FeedListBean.ContentAreaListBean,
    ) {
        TODO("Not yet implemented")
    }


}