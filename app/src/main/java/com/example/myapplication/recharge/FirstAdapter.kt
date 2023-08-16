package com.example.recharge

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.FirstBinding

class FirstAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = FirstBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name.text = item.name
        binding.name2.text = item.name2

        val lp1 = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
        val initialWidth = lp1.width
        lp1?.width  = ((ScreenUtils.getScreenWidth() - DensityUtils.dpToPx(context, 68f)).toFloat() / 3.5).toInt()
        lp1?.height = lp1.width
        holder.itemView.layoutParams = lp1
        val widthScale1 = lp1.width.toFloat() / initialWidth.toFloat()
        binding.ivImage.scaleX = widthScale1
        binding.ivImage.scaleY = widthScale1
        lp1?.height = lp1.width
        holder.itemView.layoutParams = lp1
        binding.name.textSize = 13f*widthScale1
        binding.name2.textSize = 10f*widthScale1

        //用于处理数据大小小于等于3时的居中布局效果
        if (data.size <= 3) {//处理居中
            val lp = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
            lp?.width = (ScreenUtils.getScreenWidth() - DensityUtils.dpToPx(context, 68f)) / data.size
            val widthScale = lp.width.toFloat() / initialWidth.toFloat()
            LogUtils.d(
                "ScreenUtils.getScreenWidth()= " + ScreenUtils.getScreenWidth()+"lp?.width=" + lp?.width+"; initialWidth=" + initialWidth + "; widthScale=" + widthScale
                    )

            binding.ivImage.scaleX = widthScale
            binding.ivImage.scaleY = widthScale
            lp?.height = lp.width
            holder.itemView.layoutParams = lp
            binding.name.textSize = 13f*widthScale
            binding.name2.textSize = 10f*widthScale
        } /*else {//0.7表示显示程度
            val lp = LinearLayout.LayoutParams(((context.getResources().getDisplayMetrics().widthPixels) / 4.7f).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
            holder.itemView.layoutParams = lp
        }*/

    }

}