package com.example.myapplication.recharge.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.R
import com.example.myapplication.util.DensityUtils


class ScratchCardViewGroup : ConstraintLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var textview: TextView
    private lateinit var close: ConstraintLayout
    private lateinit var container: ConstraintLayout
    private var screenWidth: Int = 0
    private var customAttrs: AttributeSet? = null

    /**
     * 这个构造方法是在代码中new的时候调用的
     * @param context
     */
    constructor(context: Context?) : super(context!!) {}

    /**
     * 这个构造方法是在xml文件中初始化调用的
     * @param context
     * @param attrs            View的xml属性
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customAttrs = attrs
        initView()
    }

    /**
     * 这个方法不常用，有前两个足够了
     * @param context
     * @param attrs
     * @param defStyleAttr        应用到View的主题风格（定义在主题中）
     */
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        screenWidth = measuredWidth
        requestLayout()
        post {
            invalidate()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        //获取图片宽度
        val imageWidth = (screenWidth - DensityUtils.dpToPx(context, 20f))
        val layoutParams1 = container.layoutParams
        val initialWidth = layoutParams1.width
        val initialHeighgt = (((initialWidth).toFloat() / 668) * 214).toInt()

        layoutParams1.width = imageWidth

        //缩放比例
        val widthScale = layoutParams1.width.toFloat() / initialWidth.toFloat()

        layoutParams1.height = initialHeighgt * widthScale.toInt()
        container.layoutParams = layoutParams1
        /*LogUtils.d(
            "initialWidth= " + initialWidth + "; initialHeighgt= " + initialHeighgt +
                    " imageWidth= " + imageWidth + "; widthScale=" + widthScale +
                    " layoutParams1.height=" + layoutParams1.height
        )*/

        val layoutParamsClose = close.layoutParams

        //获取初始宽高
        layoutParamsClose.width = imageWidth
        close.layoutParams = layoutParamsClose
    }

    private fun initView() {

        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.view_group_custom_scratch_card, this)
        imageView = findViewById<ImageView>(R.id.imageHand)
        imageView2 = findViewById<ImageView>(R.id.imageHand2)
        textview = findViewById(R.id.tvFlipCardChange)
        close = findViewById(R.id.close)
        container = findViewById<ConstraintLayout>(R.id.container)

        //绑定刮卡view
        val customView = ScratchCardView(getContext())
        container.addView(customView)

        val typedArray =
            context.obtainStyledAttributes(customAttrs, R.styleable.ScratchCardViewGroup)
        val drawable = typedArray.getDrawable(R.styleable.ScratchCardViewGroup_scratchSrc)
        val drawable2 = typedArray.getDrawable(R.styleable.ScratchCardViewGroup_scratchSrc2)
        typedArray.recycle()

        // 设置图片资源
        imageView.setImageDrawable(drawable)
        imageView2.setImageDrawable(drawable2)

        //imageView.setImageResource(R.drawable.card1)
        imageView.setImageResource(R.drawable.button2)
        imageView2.setImageResource(R.drawable.hand)

        //让按钮和手指消失
        LogUtils.d(
            "currentCloseValue" + imageView
        )
        /* if (currentCloseValue) {
             imageView.visibility = View.GONE
             imageView2.visibility = View.GONE
         }*/

        imageView.setOnClickListener {
            imageView.visibility = View.GONE
            imageView2.visibility = View.GONE
        }

        //再刮一次
        textview.setOnClickListener {
            Toast.makeText(context, "再来一次", Toast.LENGTH_SHORT).show()
            //customView.resetScratchCard()
            val customView2 = ScratchCardView(getContext())
            container.addView(customView2)
        }

        //手指动画
        val translateX = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_X, 40f)
        val translateY = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_Y, 40f)
        translateX.duration = 1800
        translateY.duration = 1800
        val AnimatorSet = AnimatorSet().apply {
            playTogether(translateX, translateY)
            playSequentially(translateX)
        }

        val translateX2 = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_X, 40f, 0f)
        val translateY2 = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_Y, 40f, 0f)
        translateX2.duration = 1800
        translateY2.duration = 1800
        val AnimatorSet2 = AnimatorSet().apply {
            playTogether(translateX2, translateY2)
            playSequentially(translateX2)
        }

        //当AnimatorSet结束时运行AnimatorSet2
        AnimatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                AnimatorSet2.start() // 重新开始动画
            }
        })
        //当AnimatorSet2结束时运行AnimatorSet
        AnimatorSet2.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                AnimatorSet.start() // 重新开始动画
            }
        })
        AnimatorSet.start()
    }


}
