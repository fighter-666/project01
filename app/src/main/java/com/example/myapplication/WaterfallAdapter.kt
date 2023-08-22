package com.example.myapplication

import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.WaterfallBinding

class WaterfallAdapter(@LayoutRes layoutResId: Int, data: MutableList<Card>) : BaseQuickAdapter<Card, BaseViewHolder>(layoutResId, data) {
    private var onItemClickListener: ((Card) -> Unit)? = null

    private val SCALE = 4 * 1.0f / 3 //图片缩放比例

    override fun convert(holder: BaseViewHolder, item: Card) {
        val binding = WaterfallBinding.bind(holder.itemView)

        //设置图片和标题
        binding.itemImage.setImageResource(item.image)
        //设置点击事件监听器
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        setCardView(holder, binding, item)
    }
    fun setOnItemClickListener(listener: (Card) -> Unit) {
        onItemClickListener = listener
    }
    private fun setCardView(holder: BaseViewHolder, binding: WaterfallBinding, card: Card) {
        //计算图片宽高
        val layoutParams = binding.itemImage.layoutParams as LinearLayout.LayoutParams
        val space = DensityUtils.dpToPx(context, 20f)
        //2列的瀑布流，屏幕宽度减去两列间的间距space所的值再除以2，计算出单列的imageview的宽度，space的值在RecyclerView初始化时传入
        val itemWidth: Float = (recyclerView.measuredWidth - space.toFloat()) / 2
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
        binding.itemImage.setLayoutParams(layoutParams)

    }


}