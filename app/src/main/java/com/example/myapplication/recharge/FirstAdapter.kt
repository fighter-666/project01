package com.example.recharge

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.FirstBinding


class FirstAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {

    override fun getDefItemCount(): Int {
        return super.getDefItemCount()
        recyclerView.measuredWidth
        recyclerView.invalidate()
    }
    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = FirstBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name.text = item.name
        binding.name2.text = item.name2

       /* val lp1 = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
        val initialWidth = lp1.width
        lp1?.width  = ((recyclerView.measuredWidth  - DensityUtils.dpToPx(context, 30f)).toFloat() / 3.5).toInt()
        lp1?.height = lp1.width
        holder.itemView.layoutParams = lp1
        val widthScale1 = lp1.width.toFloat() / initialWidth.toFloat()
        val layoutParams1 = binding.ivImage.layoutParams
        val initialImageWidth = layoutParams1.width
        layoutParams1.width = (initialImageWidth * widthScale1).toInt()
        layoutParams1.height = layoutParams1.width
        binding.ivImage.layoutParams = layoutParams1

        lp1?.height = lp1.width
        holder.itemView.layoutParams = lp1
        binding.name.textSize = 13f*widthScale1
        binding.name2.textSize = 10f*widthScale1

        //用于处理数据大小小于等于3时的居中布局效果
        if (data.size <= 3) {//处理居中
            val lp = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
            lp?.width = (recyclerView.measuredWidth - DensityUtils.dpToPx(context, 30f)) / data.size

            val layoutParams = binding.ivImage.layoutParams
            val initialImageWidth = layoutParams1.width
            layoutParams1.width = (initialImageWidth * widthScale1).toInt()
            val widthScale = layoutParams.width.toFloat() / initialImageWidth.toFloat()
            layoutParams1.height = layoutParams1.width
            binding.ivImage.layoutParams = layoutParams

            LogUtils.d(
                "ScreenUtils.getScreenWidth()= " + ScreenUtils.getScreenWidth()+"lp?.width=" + lp?.width+"; initialWidth=" + initialWidth + "; widthScale=" + widthScale
            )

            lp?.height = lp.width
          *//*  holder.itemView.layoutParams = lp

            binding.name.textSize = 13f*widthScale
            binding.name2.textSize = 10f*widthScale*//*
        }*/

    }

}