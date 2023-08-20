package com.example.myapplication.recharge

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.R
import com.example.myapplication.GetScreenUtils
import com.example.recharge.DensityUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FlipCardView : ConstraintLayout {
    private lateinit var imageView: ImageView
    private  var drawable: Drawable? = null
    private lateinit var imageViewCopy: ImageView
    private lateinit var imageView2Copy: ImageView
    private lateinit var imageView3Copy: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var textview: TextView
    private lateinit var rl: ConstraintLayout
    private  var screenWidth: Int = 0
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
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customAttrs = attrs
        initView(context)
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
    //抽到的卡片回传到自定义View，(context as ComponentActivity)将 context 对象强制转换为 ComponentActivity 类型，以便可以调用 ComponentActivity 类中定义的方法和属性。
    // 这样做可能是因为需要在 ComponentActivity 的上下文中执行一些特定的操作，或者需要使用 ComponentActivity 提供的特定功能。
    private val myActivityLauncher = (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->

        CoroutineScope(Dispatchers.Main).launch {
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result",0)
                val resources = context.resources
                drawable = result?.let { resources.getDrawable(it) }
                imageView.setImageDrawable(drawable)
            }
        }
    }

    private val myActivityLauncher2 = (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->

        CoroutineScope(Dispatchers.Main).launch {
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result",0)
                val resources = context.resources
                drawable = result?.let { resources.getDrawable(it) }
                imageView2.setImageDrawable(drawable)
            }
        }
    }

    private val myActivityLauncher3 = (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->

        CoroutineScope(Dispatchers.Main).launch {
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result",0)
                val resources = context.resources
                drawable = result?.let { resources.getDrawable(it) }
                imageView3.setImageDrawable(drawable)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        screenWidth = measuredWidth

        val imageWidth = (screenWidth - DensityUtils.dpToPx(context, 40f) )/ 3
        LogUtils.d(
            "screenWidth=" + screenWidth + "; px=" + imageWidth
        )
        val layoutParams1 = imageView.layoutParams
        layoutParams1.width = imageWidth
        layoutParams1.height = imageWidth
        imageView.layoutParams = layoutParams1
        val layoutParams1Copy = imageViewCopy.layoutParams
        layoutParams1Copy.width = imageWidth
        layoutParams1Copy.height = imageWidth
        imageViewCopy.layoutParams = layoutParams1Copy

        val layoutParams2 = imageView2.layoutParams
        layoutParams2.width = imageWidth
        layoutParams2.height = imageWidth
        imageView2.layoutParams = layoutParams2
        val layoutParams2Copy = imageView2Copy.layoutParams
        layoutParams2Copy.width = imageWidth
        layoutParams2Copy.height = imageWidth
        imageView2Copy.layoutParams = layoutParams2Copy

        val layoutParams3 = imageView3.layoutParams
        layoutParams3.width = imageWidth
        layoutParams3.height = imageWidth
        imageView3.layoutParams = layoutParams3
        val layoutParams3Copy = imageView3Copy.layoutParams
        layoutParams3Copy.width = imageWidth
        layoutParams3Copy.height = imageWidth
        imageView3Copy.layoutParams = layoutParams3Copy
        // 请求重新布局
        requestLayout()
        post {
            invalidate()
        }
        LogUtils.d(
            "55screenWidth=" + screenWidth

        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongViewCast")
    private fun initView(context: Context) {
        //获取子控件
        LayoutInflater.from(context).inflate(R.layout.flip_card, this)
        imageViewCopy = findViewById<ImageView>(R.id.card1_copy)
        imageView2Copy = findViewById<ImageView>(R.id.card2_copy)
        imageView3Copy = findViewById<ImageView>(R.id.card3_copy)
        imageView = findViewById<ImageView>(R.id.card1)
        imageView2 = findViewById<ImageView>(R.id.card2)
        imageView3 = findViewById<ImageView>(R.id.card3)
        textview = findViewById<TextView>(R.id.cl4_tv7)
        rl = findViewById(R.id.rl)

        //动态设置图片宽高







        val typedArray = context.obtainStyledAttributes(customAttrs, R.styleable.FlipCardView)
        val drawable = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc)
        val drawableCopy = typedArray.getDrawable(R.styleable.FlipCardView_cardSrcCopy)
        val drawable2 = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc2)
        val drawable3 = typedArray.getDrawable(R.styleable.FlipCardView_cardSrc3)
        typedArray.recycle()



        // 设置图片资源
        imageView.setImageDrawable(drawable)
        //imageViewCopy.setImageDrawable(drawableCopy)
        imageView2.setImageDrawable(drawable2)
        imageView3.setImageDrawable(drawable3)

        imageView.setImageResource(R.drawable.card1)
       // imageViewCopy.setImageResource(R.drawable.card1)
        imageView2.setImageResource(R.drawable.card1)
        imageView3.setImageResource(R.drawable.card1)

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

        imageView2.setOnClickListener {
            val intent = Intent(getContext(), FlipPage::class.java)
            //myActivityLauncher.launch(intent)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(imageView2, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as ComponentActivity, imagePair)

            // 启动活动并应用转场动画
            myActivityLauncher2.launch(intent, options)


        }

        imageView3.setOnClickListener {
            val intent = Intent(getContext(), FlipPage::class.java)
            //myActivityLauncher.launch(intent)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(imageView3, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as ComponentActivity, imagePair)

            // 启动活动并应用转场动画
            myActivityLauncher3.launch(intent, options)
        }
    }
}