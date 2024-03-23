package com.example.myapplication.test

import com.example.myapplication.databinding.ItemFooBinding
import com.example.myapplication.recharge.view.property.Piggy


class TestAdapter : BaseBindingQuickAdapter<Piggy, ItemFooBinding>(ItemFooBinding::inflate) {

    override fun convert(holder: BaseBindingHolder, item: Piggy) {
        holder.getViewBinding<ItemFooBinding>().apply {
            tvFoo.text = item.name
        }
    }
}



