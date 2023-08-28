package com.example.myapplication.recharge.widget

import android.annotation.SuppressLint
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
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BalanceInquiryView : ConstraintLayout {
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
        LayoutInflater.from(context).inflate(R.layout.image_text_view_layout, this)
        imageView = findViewById<ImageView>(R.id.topImage)
        imageView2 = findViewById<ImageView>(R.id.topImage2)
        textView = findViewById<TextView>(R.id.bottomText)
        rl = findViewById(R.id.rl)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BalanceInquiryView)
        val xmlImageWidth =
            typedArray.getDimensionPixelSize(R.styleable.BalanceInquiryView_imageWidth, 30)
        val xmlImageWidth2 =
            typedArray.getDimensionPixelSize(R.styleable.BalanceInquiryView_imageWidth2, 30)
        val xmlImageHeight =
            typedArray.getDimensionPixelSize(R.styleable.BalanceInquiryView_imageHeight, 30)
        val xmlImageHeight2 =
            typedArray.getDimensionPixelSize(R.styleable.BalanceInquiryView_imageHeight2, 30)
        val drawable = typedArray.getDrawable(R.styleable.BalanceInquiryView_imageSrc)
        val drawable2 = typedArray.getDrawable(R.styleable.BalanceInquiryView_imageSrc2)
        val xmlTextColor =
            typedArray.getColor(R.styleable.BalanceInquiryView_textColor, Color.BLACK)
        val content = typedArray.getString(R.styleable.BalanceInquiryView_textContent)
        typedArray.recycle()

        // 将获取到的属性值应用到您的视图或逻辑中
        // 例如，设置图片的宽度和高度
        imageView.layoutParams.width = xmlImageWidth
        imageView2.layoutParams.width = xmlImageWidth2
        imageView.layoutParams.height = xmlImageHeight
        imageView2.layoutParams.height = xmlImageHeight2

        // 设置图片资源
        imageView.setImageDrawable(drawable)
        imageView2.setImageDrawable(drawable2)

        // 设置文本颜色、大小和内容
        textView.setTextColor(xmlTextColor)
        textView.text = content

// 声明一个全局的 Animation 对象
        var animation: Animation? = null

// 初始时隐藏图片
        imageView.visibility = View.GONE
        imageView2.visibility = View.GONE

        rl.setOnClickListener {
            // 取消之前的动画
            imageView.clearAnimation()

            // 设置ImageView的图片资源
            imageView.setImageResource(R.drawable.upload1)
            imageView.visibility = View.VISIBLE
            imageView2.visibility = GONE
            textView.setTextSize(16f)
            textView.text = "查询中..."
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
            imageView.startAnimation(animation)
            //implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'
            // 使用协程延迟三秒并隐藏图片
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                // 隐藏左边的图片
                imageView.visibility = View.GONE
                imageView2.visibility = View.VISIBLE
                imageView2.setImageResource(R.drawable.refresh2)
                textView.text = "余额：10000000000.00元"
            }
        }
    }
}