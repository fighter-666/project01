package com.example.myapplication.adapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.room2.User

class UserAdapter(layoutResId: Int) : BaseQuickAdapter<User, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: User) {
        holder.addOnClickListener(R.id.item_delete,R.id.item_modify)
        val avatarView = holder.getView<ImageView>(R.id.avatar)
        Glide.with(mContext).load(item.avatar)
            .transform(CircleCrop())
            .placeholder(R.drawable.batman)
            .error(R.drawable.batman)
            .into(avatarView)
        holder.setText(R.id.name, "姓名：${item.aliasName}")
            .setText(R.id.ads, "地址：${item.ads}")
        if (item.aliasName.contains("修改")) {
            holder.setTextColor(R.id.name, ContextCompat.getColor(mContext, R.color.red))
        }
        if (item.age == 100) {
            holder.setText(R.id.age, "年龄：修改的${item.age} 岁")
            holder.setTextColor(R.id.age, ContextCompat.getColor(mContext, R.color.red))
        } else {
            holder.setText(R.id.age, "年龄：${item.age} 岁")
        }
        if (item.ads.contains("修改")) {
            holder.setTextColor(R.id.ads, ContextCompat.getColor(mContext, R.color.red))
        }
    }
}