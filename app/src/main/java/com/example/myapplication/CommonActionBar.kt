package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi


class CommonActionBar : RelativeLayout {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

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
        LayoutInflater.from(context).inflate(R.layout.view_titlebar, this)
        imageView = findViewById<ImageView>(R.id.image)
        textView = findViewById<TextView>(R.id.text)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val drawable = typedArray.getDrawable(R.styleable.TitleBar_back_src)
        val content = typedArray.getString(R.styleable.TitleBar_title)
        val barImageWidth = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageWidth3, 20)
        val barImageHeight = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageHeight3, 20)
        typedArray.recycle()

        // 将获取到的属性值应用到您的视图或逻辑中
        // 例如，设置图片的宽度和高度
        imageView.layoutParams.width = barImageWidth
        imageView.layoutParams.height = barImageHeight

        // 设置图片资源
        imageView.setImageDrawable(drawable)

        // 设置文本颜色、大小和内容
        textView.text = content
    }


}