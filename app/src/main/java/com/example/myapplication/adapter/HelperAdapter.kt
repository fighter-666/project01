package com.example.myapplication.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.databinding.AdapterHelperBinding
import com.example.myapplication.property.Piggy

class HelperAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>?) :
    BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    //一个可为空的类型变量，用于保存点击事件的监听器
    private var onItemClickListener: ((Piggy) -> Unit)? = null
    override fun convert(holder: BaseViewHolder, item: Piggy) {

        //将 holder.itemView（列表项的根视图）与生成的绑定类 HelperBinding 进行绑定。
        // 通过调用 bind() 方法，你可以获取到生成的绑定类的实例 binding。
        val binding = AdapterHelperBinding.bind(holder.itemView)

        //设置图片资源为item.image
        binding.ivHelper.setImageResource(item.image)

        //设置文本为item.name
        binding.tvHelper.text = item.name

        //设置点击事件
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }

    //他接受函数类型的参数 listener，该函数类型接受一个 Piggy 对象作为参数，并不返回任何结果
    fun setOnItemClickListener(listener: (Piggy) -> Unit) {
        //onItemClickListener 被赋值为 listener
        //从而将外部传入的监听器与适配器关联起来
        onItemClickListener = listener
    }
}