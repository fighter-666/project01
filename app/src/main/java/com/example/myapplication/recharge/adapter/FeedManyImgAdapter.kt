package com.example.myapplication.recharge.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.util.UtilOther
import com.example.myapplication.util.DensityUtils
import com.example.myapplication.util.GetScreenUtils

/**
 * 瀑布流多图区
 */
class FeedManyImgAdapter (layoutResId: Int, data: List<*>?) : BaseQuickAdapter<GetFeedListData.FeedListBean.PicListBean, BaseViewHolder>(layoutResId, data as MutableList<GetFeedListData.FeedListBean.PicListBean>?) {
    var pPos: Int = 0
    lateinit var pItem: GetFeedListData.FeedListBean

    override fun convert(helper: BaseViewHolder, item: GetFeedListData.FeedListBean.PicListBean) {
        helper.run {
            val ivPic = getView<ImageView>(R.id.ivPic)
            item.run {
                val lp1 = ivPic.layoutParams
                var mImageRatio = UtilOther.parseFloat(imageRatio)
                if (mImageRatio == 0f){
                    mImageRatio = 1f
                }
                lp1.width = (GetScreenUtils.getScreenWidth(mContext) - DensityUtils.dpToPx(mContext, 13+13+10f)) / 4
                lp1.height = (((lp1.width / mImageRatio).toInt()))
                ivPic.layoutParams = lp1
                ivPic.contentDescription = item.title
                Glide.with(mContext)
                    .load(imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                    .transition(DrawableTransitionOptions.withCrossFade())
                    //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                    //.apply(requestOptions
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivPic)
            }
        }
    }
}