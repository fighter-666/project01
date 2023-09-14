package com.example.myapplication.recharge.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.recharge.property.MultipleItem
import com.example.myapplication.recharge.property.MultipleItem.Companion.IMG
import com.example.myapplication.recharge.property.MultipleItem.Companion.IMG2
import com.example.myapplication.recharge.property.MultipleItem.Companion.TEXT

class MultipleItemQuickAdapter(data: MutableList<MultipleItem>) :
    BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    init {
        addItemType(TEXT, R.layout.widget_multiple_item_image_view)
        addItemType(IMG, R.layout.widget_multiple_item_common)
        addItemType(IMG2, R.layout.widget_multiple_item_text_view2)
    }

    override fun convert(holder: BaseViewHolder, item: MultipleItem) {
        when (holder.itemViewType) {
            TEXT -> {

            }

            IMG -> {

            }
        }
    }
}