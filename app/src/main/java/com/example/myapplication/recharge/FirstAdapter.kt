package com.example.recharge

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
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


        //用于处理数据大小小于等于3时的居中布局效果
        if (data.size <= 3) {//处理居中
            val lp = holder.itemView.layoutParams   //获取列表项视图（item view）的布局参数。
            lp?.width = (ScreenUtils.getScreenWidth() - DensityUtils.dpToPx(context, 68f)) / data.size
            val vto = binding.ivImage.viewTreeObserver
            val widthScale:Float = lp.width.toFloat() / 250

            val screenWidth = ScreenUtils.getScreenWidth()
            val screenHeight = ScreenUtils.getScreenHeight()
            Log.d("scale",screenWidth.toString())
            Log.d("scaleh",screenHeight.toString())

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