package com.example.myapplication.recharge

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
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


class FlipCardView : ConstraintLayout {
    private lateinit var imageView: ImageView
    private lateinit var imageViewCopy: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
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

    private val myActivityLauncher = (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val result = activityResult.data?.getIntExtra("result",0)
            val resources = context.resources
            val drawable = result?.let { resources.getDrawable(it) }
            imageView.setImageDrawable(drawable)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongViewCast")
    private fun initView(context: Context, attrs: AttributeSet) {
        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.flip_card, this)
        imageView = findViewById<ImageView>(R.id.card1)
        imageViewCopy = findViewById<ImageView>(R.id.card1_copy)
        imageView2 = findViewById<ImageView>(R.id.card2)
        imageView3 = findViewById<ImageView>(R.id.card3)
        textview = findViewById<TextView>(R.id.cl4_tv7)
        rl = findViewById(R.id.rl)




        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlipCardView)
        val drawable = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc)
        val drawableCopy = typedArray.getDrawable(R.styleable.FlipCardView_cardSrcCopy)
        val drawable2 = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc2)
        val drawable3 = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc3)
        typedArray.recycle()


        // 设置图片资源
        imageView.setImageDrawable(drawable)
        imageViewCopy.setImageDrawable(drawableCopy)
        imageView2.setImageDrawable(drawable2)
        imageView3.setImageDrawable(drawable3)

        //imageView.setImageResource(R.drawable.card1)
        imageViewCopy.setImageResource(R.drawable.card1)
        imageView2.setImageResource(R.drawable.card2)
        imageView3.setImageResource(R.drawable.card3)

        //点击事件
        imageView.setOnClickListener {
            val intent = Intent(getContext(), FlipPage::class.java)
            //myActivityLauncher.launch(intent)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(imageView, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as ComponentActivity, imagePair)

            // 启动活动并应用转场动画
            myActivityLauncher.launch(intent, options)


        }



    }

    fun setCardImageResource(@DrawableRes resId: Int) {
        findViewById<ImageView>(R.id.card1)?.setImageResource(resId)
    }


}