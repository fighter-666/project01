package com.example.myapplication.recharge.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.recharge.property.MultipleItem
import com.example.myapplication.recharge.property.MultipleItem.Companion.IMG
import com.example.myapplication.recharge.property.MultipleItem.Companion.IMG2
import com.example.myapplication.recharge.property.MultipleItem.Companion.TEXT

class MultipleItemQuickAdapter(data: MutableList<MultipleItem>) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    init {
        addItemType(TEXT, R.layout.image_view)
        addItemType(IMG, R.layout.text_view)
        addItemType(IMG2, R.layout.text_view2)
    }

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            TEXT -> {

            }
            IMG -> {

            }
        }
    }
}