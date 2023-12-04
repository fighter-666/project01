package com.example.myapplication.fragment

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.data.City
import com.example.myapplication.data.Province
import com.example.myapplication.data.Town
import com.example.myapplication.data.UserFluxPackageData
import com.example.myapplication.util.DensityUtils


/**
 * 地区适配器
 */
 class TestAdapter(data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder?>(data) {
    companion object {
        const val TYPE_LEVEL_0 = 0
        const val TYPE_LEVEL_1 = 1
    }

    init {
        addItemType(R.layout.item_normal_meal, R.layout.item_normal_meal)
        addItemType(R.layout.item_normal_meal_son, R.layout.item_normal_meal_son)
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

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (helper.itemViewType) {
            R.layout.item_normal_meal -> {
                val lv0 = item as UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean

                helper.run {
                    // 显示展开
                    if (lv0.productInfos.isEmpty()){
                        setGone(R.id.tvExpand, false)
                    }else{
                        setGone(R.id.tvExpand, true)
                    }

                    val llNormal = getView<LinearLayout>(R.id.llNormal)
                    if (adapterPosition == 0){
                        llNormal.setPadding(0, DensityUtils.dpToPx(mContext,9f), 0, 0)
                    }else{
                        llNormal.setPadding(0, DensityUtils.dpToPx(mContext,14f), 0, 0)
                    }

                    setText(R.id.tvTitle, lv0.title)
                    if (lv0.linkType== null){
                        setGone(R.id.ivTips, false)
                    }else{
                        setGone(R.id.ivTips, true)
                    }

                    // 左结构
                    lv0.leftStructure?.run {
                        setGone(R.id.llUsedAmount, true)
                        setText(R.id.tvUsageAmountPercent, title)
                        setText(R.id.tvUsageAmount, num)
                        setText(R.id.tvUnit, unit)
                        if ("1 "== redFlag) {
                            setTextColor(R.id.tvUsageAmountPercent, mContext.resources.getColor(R.color.red_e64b4b))
                            setTextColor(R.id.tvUsageAmount, mContext.resources.getColor(R.color.red_e64b4b))
                            setTextColor(R.id.tvUnit, mContext.resources.getColor(R.color.red_e64b4b))
                        }else{
                            setTextColor(R.id.tvUsageAmountPercent, mContext.resources.getColor(R.color.gray_999999))
                            setTextColor(R.id.tvUsageAmount, mContext.resources.getColor(R.color.black_333333))
                            setTextColor(R.id.tvUnit, mContext.resources.getColor(R.color.black_333333))
                        }
                        if (titleCornerMark == null){
                            setGone(R.id.ivUsageMark, false)
                        }else{
                            setGone(R.id.ivUsageMark, true)
                            Glide.with(mContext)
                                .load(titleCornerMark)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .error(R.drawable.ic_launcher_foreground)
                                .into(getView(R.id.ivUsageMark))
                        }
                    }?:run {
                        setGone(R.id.llUsedAmount, false)
                    }

                    // 右结构
                    lv0.rightStructure?.run {
                        setGone(R.id.llBalanceAmount, true)

                        setText(R.id.tvBalanceAmountPercent, title)
                        setText(R.id.tvBalanceAmount, num)
                        setText(R.id.tvBalanceAmountUnit, unit)
                        if ("1" == redFlag) {
                            setTextColor(R.id.tvBalanceAmountPercent, mContext.resources.getColor(R.color.red_e64b4b))
                            setTextColor(R.id.tvBalanceAmount, mContext.resources.getColor(R.color.red_e64b4b))
                            setTextColor(R.id.tvBalanceAmountUnit, mContext.resources.getColor(R.color.red_e64b4b))
                        }else{
                            setTextColor(R.id.tvBalanceAmountPercent, mContext.resources.getColor(R.color.gray_999999))
                            setTextColor(R.id.tvBalanceAmount, mContext.resources.getColor(R.color.black_333333))
                            setTextColor(R.id.tvBalanceAmountUnit, mContext.resources.getColor(R.color.black_333333))
                        }
                        if (titleCornerMark == null){
                            setGone(R.id.ivBalanceMark, false)
                        }else{
                            setGone(R.id.ivBalanceMark, true)
                            Glide.with(mContext)
                                .load(titleCornerMark)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .error(R.drawable.ic_launcher_foreground)
                                .into(getView(R.id.ivBalanceMark))
                        }
                    }?:run {
                        setGone(R.id.llBalanceAmount, false)
                    }
                }


                // 展开收起
                val tvExpand = helper.getView<TextView>(R.id.tvExpand)
                if (lv0.isExpanded) {
                    tvExpand.text = "收起详情"
                    tvExpand.setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        mContext.resources.getDrawable(R.drawable.arrow_down), null
                    )
                    helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_no_bottom_10)
                } else {
                    tvExpand.text = "展开详情"
                    tvExpand.setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        mContext.resources.getDrawable(R.drawable.arrow_down), null
                    )
                    helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_10)
                }

                // 点击展开
                helper.itemView.setOnClickListener {
                    if (lv0.productInfos.isNotEmpty()){
                        val text = if(lv0.isExpanded) "展开详情" else "收起详情"

                        val pos = helper.adapterPosition
                        if (lv0.isExpanded) {
                            // 收起
                            collapse(pos)
                        } else {
                            // 展开
                            expand(pos)
                        }
                    }
                }
            }
            R.layout.item_normal_meal_son -> {

            }
            else -> {}
        }
    }

    private fun showProvince(helper: BaseViewHolder, province: Province) {
        helper.setText(R.id.tvTitle, province.name)
        helper.getView<View>(R.id.ivExpandIcon).rotation =
            (if (province.isExpanded) 180 else 0).toFloat()
    }

    private fun showCity(helper: BaseViewHolder, city: City) {
        helper.setText(R.id.tvCity, city.name)
        helper.getView<View>(R.id.ivExpandIcon).rotation =
            (if (city.isExpanded) 180 else 0).toFloat()
    }

    private fun showTown(helper: BaseViewHolder, town: Town) {
        helper.setText(R.id.tvTown, town.name)
    }
}