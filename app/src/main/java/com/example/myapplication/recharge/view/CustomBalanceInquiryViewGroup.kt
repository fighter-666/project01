package com.example.myapplication.recharge.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewGroupCustomBalanceInquiryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CustomBalanceInquiryViewGroup : ConstraintLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var textView: TextView
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
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }

    private fun initView(context: Context, attrs: AttributeSet) {
        //获取子控件
        val binding= ViewGroupCustomBalanceInquiryBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBalanceInquiryViewGroup)
        val ivLoadWidth =
            typedArray.getDimensionPixelSize(R.styleable.CustomBalanceInquiryViewGroup_ivLoadWidth, 30)
        val ivReLoadWidth =
            typedArray.getDimensionPixelSize(R.styleable.CustomBalanceInquiryViewGroup_ivReLoadWidth, 30)
        val ivLoadHeight =
            typedArray.getDimensionPixelSize(R.styleable.CustomBalanceInquiryViewGroup_ivLoadHeight, 30)
        val ivReLoadHeight =
            typedArray.getDimensionPixelSize(R.styleable.CustomBalanceInquiryViewGroup_ivReLoadHeight, 30)
        val ivLoadSrc = typedArray.getDrawable(R.styleable.CustomBalanceInquiryViewGroup_ivLoadSrc)
        val ivLoadSrc2 = typedArray.getDrawable(R.styleable.CustomBalanceInquiryViewGroup_ivReLoadSrc)
        val tvChecktextColor =
            typedArray.getColor(R.styleable.CustomBalanceInquiryViewGroup_tvChecktextColor, Color.BLACK)
        val tvChecktextContent = typedArray.getString(R.styleable.CustomBalanceInquiryViewGroup_tvChecktextContent)
        typedArray.recycle()

        // 将获取到的属性值应用到您的视图或逻辑中
        // 例如，设置图片的宽度和高度
        binding.ivLoad.layoutParams.width = ivLoadWidth
        binding.ivReLoad.layoutParams.width = ivReLoadWidth
        binding.ivLoad.layoutParams.height = ivLoadHeight
        binding.ivReLoad.layoutParams.height = ivReLoadHeight

        // 设置图片资源
        binding.ivLoad.setImageDrawable(ivLoadSrc)
        binding.ivReLoad.setImageDrawable(ivLoadSrc2)

        // 设置文本颜色、大小和内容
        binding.tvCheck.setTextColor(tvChecktextColor)
        binding.tvCheck.text = tvChecktextContent

// 声明一个全局的 Animation 对象
        var animation: Animation? = null

// 初始时隐藏图片
        binding.ivLoad.visibility = View.GONE
        binding.ivReLoad.visibility = View.GONE

        binding.cl.setOnClickListener {
            // 取消之前的动画
            binding.ivLoad.clearAnimation()

            // 设置ImageView的图片资源
            binding.ivLoad.setImageResource(R.drawable.upload1)
            binding.ivLoad.visibility = View.VISIBLE
            binding.ivReLoad.visibility = GONE
            binding.tvCheck.setTextSize(16f)
            binding.tvCheck.text = "查询中..."
            // 设置文本居中显示

            // 创建并开始属性动画
            //Animation.RELATIVE_TO_SELF, 0.5f 表示旋转的中心点相对于自身的横向和纵向位置为 0.5，即图片的中心点。
            animation = RotateAnimation(
                0f,
                720f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            animation?.duration = 3000
            binding.ivLoad.startAnimation(animation)
            //implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'
            // 使用协程延迟三秒并隐藏图片
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                // 隐藏左边的图片
                binding.ivLoad.visibility = View.GONE
                binding.ivReLoad.visibility = View.VISIBLE
                binding.ivReLoad.setImageResource(R.drawable.refresh2)
                binding.tvCheck.text = "余额：10000000000.00元"
            }
        }
    }
}