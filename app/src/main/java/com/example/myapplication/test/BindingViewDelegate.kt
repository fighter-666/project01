package com.example.myapplication.test

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.drakeet.multitype.ItemViewDelegate

abstract class BindingViewDelegate<T, VB : ViewBinding> (
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB
): ItemViewDelegate<T, BindingViewDelegate.BindingViewHolder<VB>>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup) : BindingViewHolder<VB> =
        BindingViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, item: T) {
        onBindViewHolder(holder.binding, item, holder.adapterPosition)
    }

    abstract fun onBindViewHolder(holder: VB, item: T, position: Int)

    class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}
