package com.example.myapplication

import android.R
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.WaterfallBinding


class WaterfallAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) : BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    private var onItemClickListener: ((Piggy) -> Unit)? = null
    private val STANDARD_SCALE = 1.1 //当图片宽高比例大于STANDARD_SCALE时，采用3:4比例，小于时，则采用1:1比例

    private val SCALE = 4 * 1.0f / 3 //图片缩放比例

    private val cards: List<Card> = ArrayList()

    override fun convert(holder: BaseViewHolder, item: Piggy) {
        val binding = WaterfallBinding.bind(holder.itemView)


        //设置图片和标题
        binding.itemImage.setImageResource(item.image)
        binding.itemTitle.text = item.name

        //设置点击事件监听器
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    fun setOnItemClickListener(listener: (Piggy) -> Unit) {
        onItemClickListener = listener
    }


}