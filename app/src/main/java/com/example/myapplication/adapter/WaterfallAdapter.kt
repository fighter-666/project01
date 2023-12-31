package com.example.myapplication.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.databinding.AdapterWaterfallBinding
import com.example.myapplication.recharge.view.property.Card
import com.example.myapplication.util.DensityUtils

class WaterfallAdapter(@LayoutRes layoutResId: Int, data: MutableList<Card>) :
    BaseQuickAdapter<Card, BaseViewHolder>(layoutResId, data) {
    private var onItemClickListener: ((Card) -> Unit)? = null

    private val SCALE = 4 * 1.0f / 3 //图片缩放比例

    override fun convert(holder: BaseViewHolder, item: Card) {
        //将 holder.itemView（列表项的根视图）与生成的绑定类 WaterfallBinding 进行绑定。
        // 通过调用 bind() 方法，你可以获取到生成的绑定类的实例 binding。
        val binding = AdapterWaterfallBinding.bind(holder.itemView)

        //设置图片和标题
        binding.ivWaterfallAdapter.setImageResource(item.image)
        //设置点击事件监听器
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        //setCardView(holder, binding, item)
    }

    fun setOnItemClickListener(listener: (Card) -> Unit) {
        onItemClickListener = listener
    }

    private fun setCardView(holder: BaseViewHolder, binding: AdapterWaterfallBinding, card: Card) {

        //计算图片宽高
        //获取名为 itemImage 的 ImageView 的布局参数（LayoutParams）
        val layoutParams = binding.ivWaterfallAdapter.layoutParams

        //获取边距
        val space = DensityUtils.dpToPx(mContext, 20f)

        //2列的瀑布流，屏幕宽度减去两列间的间距space所的值再除以2，
        // 计算出单列的imageview的宽度，space的值在RecyclerView初始化时传入
        val itemWidth: Float = (recyclerView.measuredWidth - space.toFloat()) / 2

        //给图片的宽度赋值
        layoutParams.width = itemWidth.toInt()
        val width = card.width.toFloat()
        val height = card.height.toFloat()
        val scale = height / width

        //holder 的布局位置（layoutPosition）除以 2 的余数等于 1，
        // 表示列表项的位置是奇数（从 0 开始计数
        if (holder.layoutPosition % 2 == 1) {
            //采用3:4显示
            layoutParams.height = (itemWidth * SCALE).toInt()
        } else {
            //采用1:1显示
            layoutParams.height = itemWidth.toInt()
        }
        binding.ivWaterfallAdapter.setLayoutParams(layoutParams)
    }
}