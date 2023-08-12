package com.example.myapplication.recharge

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import com.example.myapplication.R
import androidx.core.util.Pair
import com.blankj.utilcode.util.ScreenUtils
import com.example.recharge.DensityUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ScratchCardView : ConstraintLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var textview: TextView
    private lateinit var rl: ConstraintLayout

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
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    /**
     * 这个方法不常用，有前两个足够了
     * @param context
     * @param attrs
     * @param defStyleAttr        应用到View的主题风格（定义在主题中）
     */
    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongViewCast")
    private fun initView(context: Context, attrs: AttributeSet) {
        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.scratch_card, this)
        imageView = findViewById<ImageView>(R.id.image_hand)
        imageView2 = findViewById<ImageView>(R.id.image_hand2)
        val container = findViewById<LinearLayout>(R.id.container)
        val customView = ScratchCard(getContext())
        container.addView(customView)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScratchCardView)
        val drawable = typedArray.getDrawable(R.styleable.ScratchCardView_scratchSrc)
        val drawable2 = typedArray.getDrawable(R.styleable.ScratchCardView_scratchSrc2)
        typedArray.recycle()

        // 设置图片资源
        imageView.setImageDrawable(drawable)
        imageView2.setImageDrawable(drawable2)

        //imageView.setImageResource(R.drawable.card1)
        imageView.setImageResource(R.drawable.button2)
        imageView2.setImageResource(R.drawable.hand)

        //点击事件
        imageView.setOnClickListener {
            imageView.visibility = View.GONE
            imageView2.visibility = View.GONE
        }

        val translateX = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_X, 30f)
        val translateY = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_Y, 30f)
        val translateX2 = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_X, 30f)
        val translateY2 = ObjectAnimator.ofFloat(imageView2, View.TRANSLATION_Y, 30f)

        translateX.duration = 1800
        translateY.duration = 1800
        translateX2.duration = 1800
        translateY2.duration = 1800
        val combinedAnimatorSet = AnimatorSet().apply {
            playTogether(translateX, translateY)
            playTogether(translateX2, translateY2)
            playSequentially(translateX, translateY)
        }
        combinedAnimatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                combinedAnimatorSet.start() // 重新开始动画
            }
        })

        combinedAnimatorSet.start()
    }

    
}