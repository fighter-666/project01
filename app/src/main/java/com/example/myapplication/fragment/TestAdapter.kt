package com.example.myapplication.fragment

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.data.UserFluxPackageData
import com.example.myapplication.recharge.util.UtilOther
import com.example.myapplication.util.DensityUtils


/**
 * 地区适配器
 */
 class TestAdapter(data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder?>(data) {

    init {
        addItemType(R.layout.item_normal_meal, R.layout.item_normal_meal)
        addItemType(R.layout.item_normal_meal_son, R.layout.item_normal_meal_son)
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
                        setOnClickListener(R.id.ivTips, View.OnClickListener {
                            Toast.makeText(mContext, "敬请期待", Toast.LENGTH_SHORT).show()
                        })
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
                val ivExpand = helper.getView<ImageView>(R.id.ivExpand)
                if (lv0.isExpanded) {
                    tvExpand.text = "收起详情"
                    ivExpand.setImageResource(R.drawable.ic_arrow_up2)
                    helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_no_bottom_10)
                } else {
                    tvExpand.text = "展开详情"
                    ivExpand.setImageResource(R.drawable.arrow_down)
                    helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_10)
                }





                // 点击展开
                helper.itemView.setOnClickListener {
                    if (lv0.productInfos.isNotEmpty()){
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
                val lv1 = item as UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean.ProductInfosBean
                helper.run {
                    lv1.run {
                        setText(R.id.tvTitle, title)
                        setText(R.id.tvOverdueTime, outOfServiceTime)
                        Glide.with(mContext)
                            .load(leftIcon)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(getView(R.id.ivUsageIcon))

                        if (titleIcon == null){
                            helper.setGone(R.id.ivTransfer, false)
                        }else{
                            helper.setGone(R.id.ivTransfer, true)
                            Glide.with(mContext)
                                .load(titleIcon)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                                //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(getView(R.id.ivTransfer))
                        }

                        val bar = helper.getView<ProgressBar>(R.id.progressPck)
                        bar.progress = UtilOther.parseInt(progressBar)

                        if (linkType == null){
                            helper.setGone(R.id.ivTips, false)
                        }else{
                            helper.setGone(R.id.ivTips, true)
                        }

                        // 是否不限量
                        if ("1" == isInfiniteAmount) {
                            helper.setGone(R.id.llInfinite, true)
                            helper.setGone(R.id.llNormal, false)
                            setText(R.id.tvUsageTitle, infiniteTitle)
                            setText(R.id.tvInfiniteUsageAmount, infiniteValue)
                            setText(R.id.tvUsageUnit, infiniteUnit)
                            // 失效
                            if ("1" == isInvalid) {
                                setTextColor(R.id.tvInfiniteUsageAmount, mContext.resources.getColor(R.color.gray_999999))
                                setTextColor(R.id.tvUsageUnit, mContext.resources.getColor(R.color.gray_999999))
                                helper.setGone(R.id.ivInvalid, true)
                            }else{
                                setTextColor(R.id.tvInfiniteUsageAmount, mContext.resources.getColor(R.color.gray_666666))
                                setTextColor(R.id.tvUsageUnit, mContext.resources.getColor(R.color.gray_666666))
                                helper.setGone(R.id.ivInvalid, false)
                            }
                        }else{
                            helper.setGone(R.id.llInfinite, false)
                            helper.setGone(R.id.llNormal, true)
                            // 失效
                            if ("1" == isInvalid) {
                                helper.setGone(R.id.ivInvalid, true)
                                // 灰色进度
                                val progressDrawable = mContext.resources.getDrawable(R.drawable.progressbar_mini_gray)
                                progressDrawable.bounds = bar.progressDrawable.bounds
                                bar.progressDrawable = progressDrawable

                                setTextColor(R.id.tvRatableAmount, mContext.resources.getColor(R.color.gray_999999))
                                setTextColor(R.id.tvRightHighlightTitle, mContext.resources.getColor(R.color.gray_999999))
                            }else{
                                helper.setGone(R.id.ivInvalid, false)
                                // 蓝色进度
                                val progressDrawable =
                                    mContext.resources.getDrawable(R.drawable.progressbar_mini_blue)
                                progressDrawable.bounds = bar.progressDrawable.bounds
                                bar.progressDrawable = progressDrawable

                                setTextColor(R.id.tvRatableAmount, mContext.resources.getColor(R.color.blue_1C8EFE))
                                setTextColor(R.id.tvRightHighlightTitle, mContext.resources.getColor(R.color.blue_1C8EFE))
                            }

                            // 左下角
                            setText(R.id.tvBalanceAmount, leftTitle)
                            setText(R.id.tvRatableAmount, leftHighlight)
                            // 右下角
                            setText(R.id.tvRightTitle, rightTitle)
                            setText(R.id.tvRightHighlightTitle, rightHighlight)
                            setText(R.id.tvRightCommon, rightCommon)
                        }
                    }
                }

                //删除子列表
                helper.itemView.setOnClickListener(View.OnClickListener {
                    val pos: Int = helper.adapterPosition
                    // 先获取到当前 item 的父 positon，再移除自己
                    val positionAtAll = getParentPositionInAll(pos)
                    remove(pos)
                    if (positionAtAll != -1) {
                        val multiItemEntity = data[positionAtAll] as IExpandable<*>
                        if (!hasSubItems(multiItemEntity)) {
                            remove(positionAtAll)
                        }
                    }
                })

                val pos = getParentPosition(item)
                (data[pos] as UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean).run {
                    when (productInfos.indexOf(item)) {
                        productInfos.size - 1 -> {
                            helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_no_top_10)
                            helper.setGone(R.id.viewLine, false)
                        }
                        else ->{
                            helper.setBackgroundRes(R.id.llbg, R.drawable.bg_gray_stroke_middle)
                            helper.setGone(R.id.viewLine, true)
                        }
                    }

                    when (productInfos.indexOf(item)) {
                        0 -> {
                            helper.setGone(R.id.llLine, true)
                        }
                        else ->{
                            helper.setGone(R.id.llLine, false)
                        }
                    }
                }
            }
            else -> {}
        }
    }
}