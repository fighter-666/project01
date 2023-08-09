package com.example.recharge

import android.view.Gravity
import android.view.ViewGroup
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

        if (data.size <= 3) {//处理居中
            val lp = holder.itemView.layoutParams
            lp?.width = (ScreenUtils.getScreenWidth() - DensityUtils.dpToPx(context, 65f)) / data.size
            lp?.height = lp.width
            binding.name.textSize = 14f
            binding.name2.textSize = 12f


            holder.itemView.layoutParams = lp


        } /*else {//0.7表示显示程度
            val lp = LinearLayout.LayoutParams(((context.getResources().getDisplayMetrics().widthPixels) / 4.7f).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
            holder.itemView.layoutParams = lp
        }*/

    }

}