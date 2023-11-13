package com.example.myapplication.recharge.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.util.DensityUtils
import com.gongwen.marqueen.MarqueeFactory

/**
 * feed流评论区
 */
class FeedCommentDataMF(mContext: Context, mIsCare: Boolean) : MarqueeFactory<RelativeLayout, GetFeedListData.FeedListBean.PicAreaBean.CommentListBean>(mContext) {
    var mIsCare = false // 是否关爱版
    init {
        this.mIsCare = mIsCare
    }

    override fun generateMarqueeItemView(data: GetFeedListData.FeedListBean.PicAreaBean.CommentListBean): RelativeLayout {
        val relativeLayout = RelativeLayout(mContext)
        // 文本
        val params2 = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        val mView = TextView(mContext)
        if (mIsCare){
            mView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        }else{
            mView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11f)
        }

        try {
            mView.setTextColor(Color.parseColor(data.fontColor))
        } catch (e: Exception) {
            mView.setTextColor(mContext.resources.getColor(R.color.white))
        }
        mView.text = data.title
        mView.isSingleLine = true
        mView.ellipsize = TextUtils.TruncateAt.END
        mView.setBackgroundResource(R.drawable.bg_blue_4d1ee2ff_corner_12dp)
        mView.setPadding(
            DensityUtils.dpToPx(mContext,6f),
            DensityUtils.dpToPx(mContext,3f),
            DensityUtils.dpToPx(mContext,6f),
            DensityUtils.dpToPx(mContext,3f)
        )
        val mGroupDrawable = mView.background as GradientDrawable
        mGroupDrawable.mutate()
        // 背景颜色
        try {
            mGroupDrawable.setColor(Color.parseColor(data.backgroundColor))
        } catch (e: Exception) {
            mGroupDrawable.setColor(mContext.resources.getColor(R.color.blue_4d1ee2ff))
        }
        relativeLayout.addView(mView, params2)
        return relativeLayout
    }
}