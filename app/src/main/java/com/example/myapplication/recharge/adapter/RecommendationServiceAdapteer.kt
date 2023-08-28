package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.AdapterRecommendationServiceBinding
import com.example.myapplication.recharge.property.Piggy
import com.example.myapplication.util.DensityUtils


class RecommendationServiceAdapteer(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) :
    BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = AdapterRecommendationServiceBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name.text = item.name
        binding.name2.text = item.name2

        //用于处理数据大小小于等于3时的居中布局效果
        if (data.size <= 3) {//处理居中
            val lp = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
            //获取初始宽度
            val initialWidth = lp.width

            //三等分的item的宽度
            lp?.width = (recyclerView.measuredWidth - DensityUtils.dpToPx(context, 30f)) / data.size
            lp?.height = lp.width

            //缩放比例
            val widthScale = lp.width.toFloat() / initialWidth.toFloat()
            holder.itemView.layoutParams = lp

            //图片自适应
            val layoutParams = binding.ivImage.layoutParams
            layoutParams.width = (layoutParams.width * widthScale).toInt()
            layoutParams.height = layoutParams.width
            binding.ivImage.layoutParams = layoutParams

            //字体大小自适应
            binding.name.textSize = 13f * widthScale
            binding.name2.textSize = 10f * widthScale

        } else {
            val lp1 = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
            //获取初始宽度
            val initialWidth = lp1.width

            //3.5等分的item的宽度
            lp1?.width = ((recyclerView.measuredWidth - DensityUtils.dpToPx(
                context,
                30f
            )).toFloat() / 3.5).toInt()
            lp1?.height = lp1.width
            holder.itemView.layoutParams = lp1

            //缩放比例
            val widthScale1 = lp1.width.toFloat() / initialWidth.toFloat()

            //图片自适应
            val layoutParams1 = binding.ivImage.layoutParams
            layoutParams1.width = (layoutParams1.width * widthScale1).toInt()
            layoutParams1.height = layoutParams1.width
            binding.ivImage.layoutParams = layoutParams1

            //字体大小自适应
            binding.name.textSize = 13f * widthScale1
            binding.name2.textSize = 10f * widthScale1
        }

    }

}