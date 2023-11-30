package com.example.myapplication.fragment

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town


/**
 * 地区适配器
 */
abstract class LocationAdapter(data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder?>(data) {
    init {
        // 指定 type 对应的布局资源
        addItemType(R.layout.item_province, R.layout.item_province)
        addItemType(R.layout.item_city, R.layout.item_city)
        addItemType(R.layout.item_town, R.layout.item_town)
        setOnItemClickListener()
    }

    // 设置 Item 点击事件监听器
    private fun setOnItemClickListener() {
        val onItemClickListener =
            OnItemClickListener { adapter, view, position ->
                val item = getItem(position)
                if (item !is AbstractExpandableItem<*>) {
                    return@OnItemClickListener
                }
                if ((item as AbstractExpandableItem<*>).isExpanded) {
                    // 收起被点击 Item 的子列表
                    collapse(position + headerLayoutCount)
                } else {
                    // 展开被点击 Item 的子列表
                    expand(position + headerLayoutCount)
                }
            }
        setOnItemClickListener(onItemClickListener)
    }

    protected override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (helper.itemViewType) {
            R.layout.item_province -> showProvince(helper, item as Province)
            R.layout.item_city -> showCity(helper, item as City)
            R.layout.item_town -> showTown(helper, item as Town)
            else -> {}
        }
    }

    private fun showProvince(helper: BaseViewHolder, province: Province) {
        helper.setText(R.id.tvProvince, province.name)
        helper.getView<View>(R.id.ivExpandIcon).rotation =
            (if (province.isExpanded) 90 else 0).toFloat()
    }

    private fun showCity(helper: BaseViewHolder, city: City) {
        helper.setText(R.id.tvCity, city.name)
        helper.getView<View>(R.id.ivExpandIcon).rotation =
            (if (city.isExpanded) 90 else 0).toFloat()
    }

    private fun showTown(helper: BaseViewHolder, town: Town) {
        helper.setText(R.id.tvTown, town.name)
    }
}