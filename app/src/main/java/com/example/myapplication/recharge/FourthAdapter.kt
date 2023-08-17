package com.example.recharge

import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.GetScreenUtils
import com.example.myapplication.databinding.FourthBinding

class FourthAdapter(@LayoutRes layoutResId: Int, data: MutableList<Cards>?) : BaseQuickAdapter<Cards, BaseViewHolder>(layoutResId, data) {

    private val SCALE = 3 * 1.0f / 3 //图片缩放比例

    override fun convert(holder: BaseViewHolder, item: Cards) {
        val binding = FourthBinding.bind(holder.itemView)
        binding.ivImage.setImageResource(item.image)
        binding.name2.setBackgroundResource(item.backGround)
        binding.name3.setBackgroundResource(item.backGround2)
        binding.name.text = item.name
        binding.name2.text = item.name2
        binding.name3.text = item.name3
        binding.name4.text = item.name4
        binding.name5.text = item.name5
        binding.name6.text = item.name6

        if (item.name2 != "0") {
            binding.name2.setText(item.name2)
            binding.name2.isGone = false
        } else {
            binding.name2.isGone = true
        }

        if (item.name3 != "0") {
            binding.name3.setText(item.name3)
            binding.name3.isGone = false
        } else {
            binding.name3.isGone = true
        }

        if (item.name4 != "0") {
            binding.name4.setText(item.name4)
            binding.name4.isGone = false
        } else {
            binding.name4.isGone = true
        }

        if (item.name5 != "0") {
            binding.name5.setText(item.name5)
            binding.name5.isGone = false
        } else {
            binding.name5.isGone = true
        }

        if (item.name6 != "0") {
            binding.name6.setText(item.name6)
            binding.name6.isGone = false
        } else {
            binding.name6.isGone = true
        }

        setCardView(holder, binding, item)
    }

    private fun setCardView(holder: BaseViewHolder, binding: FourthBinding, card: Cards) {
        //计算图片宽高
        val layoutParams = binding.ivImage.layoutParams as ConstraintLayout.LayoutParams
        val space = DensityUtils.dpToPx(context, 44f)
        //2列的瀑布流，屏幕宽度减去两列间的间距space所的值再除以2，计算出单列的imageview的宽度，space的值在RecyclerView初始化时传入
        val itemWidth: Float = (GetScreenUtils.getScreenWidth(context) - space.toFloat()) / 2
        layoutParams.width = itemWidth.toInt()
        val width = card.width.toFloat()
        val height = card.height.toFloat()
        val scale = height / width
        if (holder.layoutPosition % 2 == 1) {
            //采用3:4显示
            layoutParams.height = (itemWidth * SCALE).toInt()
        } else {
            //采用1:1显示
            layoutParams.height = itemWidth.toInt()
        }
        binding.ivImage.setLayoutParams(layoutParams)

    }
}