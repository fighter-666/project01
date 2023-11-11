package com.example.myapplication.recharge.adapter

import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.AdapterRechargeContentarealistPiclistDoubleBinding
import com.example.myapplication.recharge.data.GetFeedListData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentAreaListGridAdapter(
    @LayoutRes layoutResId: Int,
    data: MutableList<GetFeedListData.FeedListBean.PicListBean>,
) :
    BaseQuickAdapter<GetFeedListData.FeedListBean.PicListBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean.PicListBean) {
        val binding = AdapterRechargeContentarealistPiclistDoubleBinding.bind(holder.itemView)
        //卡片锁宽等比缩放（imageRatio用来计算高度）
        val layoutParams = binding.ivImageUrl.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.dimensionRatio = item.imageRatio // 例如，设置宽高比为16:9

        binding.ivImageUrl.layoutParams = layoutParams

        Glide.with(mContext)
            .load(item.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
            //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivImageUrl)
    }
}