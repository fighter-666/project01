package com.example.recharge

import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.ThirdBinding

class ThirdAdapter(@LayoutRes layoutResId: Int, data: MutableList<Change>?) : BaseQuickAdapter<Change, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Change) {
        val binding = ThirdBinding.bind(holder.itemView)
        binding.llBg1.setBackgroundResource(item.backGround)
        binding.llBg2.setBackgroundResource(item.backGround2)
        binding.name1.text = item.name1
        binding.name1.setBackgroundResource(item.backGround3)
        binding.name4.setBackgroundResource(item.backGround4)
        binding.name2.text = item.name2
        binding.name3.text = item.name3
        binding.name4.text = item.name4

        if (holder.adapterPosition == 0) { // 假设要调整第一项的文字大小
            val textSize = 24f // 设置字体大小
            binding.name3.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            binding.name3.gravity = Gravity.CENTER
            binding.name3.gravity = Gravity.CENTER_VERTICAL
        }
    }

}