package com.example.myapplication.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.activity.Return


class TitleBar : RelativeLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var textView: TextView
    private lateinit var textView2: TextView

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
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongViewCast", "CustomViewStyleable")
    private fun initView(context: Context, attrs: AttributeSet) {
        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.view_titlebar, this)
        imageView = findViewById<ImageView>(R.id.image)
        imageView2 = findViewById<ImageView>(R.id.image2)
        textView = findViewById<TextView>(R.id.text)
        textView2 = findViewById<TextView>(R.id.rightText)

        //在组件活动（ComponentActivity）中注册一个用于启动活动并接收结果的活动结果协议（ActivityResultContract）
        val myActivityLauncher = (context as ComponentActivity).registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result",0)
                if (result == 1){
                    //退出
                    context.finish()
                }
            }
        }

        //有返回值的跳转页面
        imageView.setOnClickListener {
            //getContext() 获取上下文
            val intent = Intent(getContext(), Return::class.java)
            myActivityLauncher.launch(intent)
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val drawable = typedArray.getDrawable(R.styleable.TitleBar_back_src)
        val drawable2 = typedArray.getDrawable(R.styleable.TitleBar_right_src)
        val content = typedArray.getString(R.styleable.TitleBar_title)
        val content2 = typedArray.getString(R.styleable.TitleBar_right_title)
        val barImageWidth = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageWidth3, 20)
        val right_ImageWidth = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageWidth3, 20)
        val barImageHeight = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageHeight3, 20)
        val right_ImageHeight = typedArray.getDimensionPixelSize(R.styleable.TitleBar_imageHeight3, 20)
        typedArray.recycle()

        // 将获取到的属性值应用到您的视图或逻辑中
        // 例如，设置图片的宽度和高度
        imageView.layoutParams.width = barImageWidth
        imageView2.layoutParams.width = right_ImageWidth
        imageView.layoutParams.height = barImageHeight
        imageView2.layoutParams.height = right_ImageHeight

        // 设置图片资源
        imageView.setImageDrawable(drawable)
        imageView2.setImageDrawable(drawable2)

        // 设置文本颜色、大小和内容
        textView.text = content
        textView2.text = content2
    }
}