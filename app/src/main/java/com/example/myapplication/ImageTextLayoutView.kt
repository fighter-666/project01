package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class ImageTextLayoutView : RelativeLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var textView: TextView
    private lateinit var rl: RelativeLayout

    /**
     * 这个构造方法是在代码中new的时候调用的
     * @param context
     */
    constructor(context: Context?) : super(context) {}

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
        context,
        attrs,
        defStyleAttr
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongViewCast")
    private fun initView(context: Context, attrs: AttributeSet) {
        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.image_text_view_layout, this)
        imageView = findViewById<ImageView>(R.id.top_image)
        imageView2 = findViewById<ImageView>(R.id.top_image2)
        textView = findViewById<TextView>(R.id.bottom_text)
        rl = findViewById(R.id.rl)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextCustomView)
        val xmlImageWidth = typedArray.getDimensionPixelSize(R.styleable.ImageTextCustomView_imageWidth, 30)
        val xmlImageWidth2 = typedArray.getDimensionPixelSize(R.styleable.ImageTextCustomView_imageWidth2, 30)
        val xmlImageHeight = typedArray.getDimensionPixelSize(R.styleable.ImageTextCustomView_imageHeight, 30)
        val xmlImageHeight2 = typedArray.getDimensionPixelSize(R.styleable.ImageTextCustomView_imageHeight2, 30)
        val drawable = typedArray.getDrawable(R.styleable.ImageTextCustomView_imageSrc)
        val drawable2 = typedArray.getDrawable(R.styleable.ImageTextCustomView_imageSrc2)
        val xmlTextColor = typedArray.getColor(R.styleable.ImageTextCustomView_textColor, Color.BLACK)
        val xmlTextSize = typedArray.getDimension(R.styleable.ImageTextCustomView_textSize, 12f)
        val content = typedArray.getString(R.styleable.ImageTextCustomView_textContent)
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
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,xmlTextSize)
        textView.text = content

// 声明一个全局的 Animation 对象
        var animation: Animation? = null
        // 设置文本居中显示
        textView.gravity = Gravity.CENTER

// 初始时隐藏图片
        imageView.visibility = View.GONE
        imageView2.visibility = View.GONE


        rl.setOnClickListener {
            // 取消之前的动画
            imageView.clearAnimation()

            // 设置ImageView的图片资源
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.upload1)
            textView.text = "查询中..."
            textView.setCompoundDrawables(null, null, null, null)

            // 创建并开始属性动画
            animation = RotateAnimation(0f, 720f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
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
                // 将12dp转换为像素值
                val marginPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12f, resources.displayMetrics).toInt()

// 设置文本内容
                textView.text = "余额：58.00元"
                textView.maxLines = 1
                textView.ellipsize = TextUtils.TruncateAt.END
            }
        }
    }


}