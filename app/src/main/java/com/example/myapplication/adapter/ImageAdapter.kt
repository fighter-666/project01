package com.example.myapplication.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.DataBean
import com.youth.banner.adapter.BannerAdapter


/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(mDatas: List<DataBean?>?) :
    BannerAdapter<DataBean?, ImageAdapter.BannerViewHolder?>(mDatas) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.adjustViewBounds = true
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: DataBean?, position: Int, size: Int) {
        if (holder != null) {
            holder.imageView.setImageResource(data?.imageRes!!)
        }
    }



    inner class BannerViewHolder(var imageView: ImageView) :
        RecyclerView.ViewHolder(imageView)
}

