package com.example.myapplication.recharge.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewGroupCustomFlipCardBinding
import com.example.myapplication.recharge.activity.FlipPage
import com.example.myapplication.util.DensityUtils


class CustomFlipCardViewGroup : ConstraintLayout {
    private lateinit var binding: ViewGroupCustomFlipCardBinding
    private var drawable: Drawable? = null
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
        initView(context)
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

    //抽到的卡片回传到自定义View，(context as ComponentActivity)将 context 对象强制转换为 ComponentActivity 类型，
    // 以便可以调用 ComponentActivity 类中定义的方法和属性。
    // 这样做可能是因为需要在 ComponentActivity 的上下文中执行一些特定的操作，或者需要使用 ComponentActivity 提供的特定功能。
    private val myActivityLauncher =
        (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result", 0)
                val drawable =
                    result?.let { ResourcesCompat.getDrawable(context.resources, it, null) }
                binding.ivFirstCard.setImageDrawable(drawable)
            }
        }

    //第二张卡片
    private val myActivityLauncher2 =
        (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result", 0)
                drawable = result?.let { ResourcesCompat.getDrawable(context.resources, it, null) }
                binding.ivSecondCard.setImageDrawable(drawable)
            }
        }

    //第三张卡片
    private val myActivityLauncher3 =
        (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val result = activityResult.data?.getIntExtra("result", 0)
                drawable = result?.let { ResourcesCompat.getDrawable(context.resources, it, null) }
                binding.ivThirdCard.setImageDrawable(drawable)
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取可用屏幕宽度
        screenWidth = measuredWidth
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // 卡片三等分
        val imageWidth = (screenWidth - DensityUtils.dpToPx(context, 40f)) / 3
        val layoutParams1 =  binding.ivFirstCard.layoutParams
        layoutParams1.width = imageWidth
        layoutParams1.height = imageWidth
        binding.ivFirstCard.layoutParams = layoutParams1

        //重叠的卡片当作背景
        val layoutParams1Copy =  binding.ivFirstCardBackground.layoutParams
        layoutParams1Copy.width = imageWidth
        layoutParams1Copy.height = imageWidth
        binding.ivFirstCardBackground.layoutParams = layoutParams1Copy

        //第二张卡片
        val layoutParams2 =  binding.ivSecondCard.layoutParams
        layoutParams2.width = imageWidth
        layoutParams2.height = imageWidth
        binding.ivSecondCard.layoutParams = layoutParams2

        //重叠的第二张卡片
        val layoutParams2Copy =  binding.ivSecondCardBackground.layoutParams
        layoutParams2Copy.width = imageWidth
        layoutParams2Copy.height = imageWidth
        binding.ivSecondCardBackground.layoutParams = layoutParams2Copy

        //第三张卡片
        val layoutParams3 = binding.ivThirdCard.layoutParams
        layoutParams3.width = imageWidth
        layoutParams3.height = imageWidth
        binding.ivThirdCard.layoutParams = layoutParams3

        //重叠的卡片
        val layoutParams3Copy = binding.ivThirdCardBackground.layoutParams
        layoutParams3Copy.width = imageWidth
        layoutParams3Copy.height = imageWidth
        binding.ivThirdCardBackground.layoutParams = layoutParams3Copy
    }

    private fun initView(context: Context) {
        //获取子控件
        binding = ViewGroupCustomFlipCardBinding.inflate(LayoutInflater.from(context), this, true)
        val typedArray = context.obtainStyledAttributes(customAttrs, R.styleable.CustomFlipCardViewGroup)
        val drawable = typedArray.getDrawable(R.styleable.CustomFlipCardViewGroup_cardSrc)
        val drawable2 = typedArray.getDrawable(R.styleable.CustomFlipCardViewGroup_cardSrc2)
        val drawable3 = typedArray.getDrawable(R.styleable.CustomFlipCardViewGroup_cardSrc3)
        typedArray.recycle()

        // 设置图片资源
        binding.ivFirstCard.setImageDrawable(drawable)
        binding.ivSecondCard.setImageDrawable(drawable2)
        binding.ivThirdCard.setImageDrawable(drawable3)

        //获取图片的资源
        binding.ivFirstCard.setImageResource(R.drawable.card1)
        binding.ivSecondCard.setImageResource(R.drawable.card1)
        binding.ivThirdCard.setImageResource(R.drawable.card1)


        //第一张卡片的点击事件
        binding.ivFirstCard.setOnClickListener {
            //跳转页面
            val intent = Intent(getContext(), FlipPage::class.java)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(binding.ivFirstCard, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as ComponentActivity,
                imagePair
            )

            // 启动活动并应用转场动画
            myActivityLauncher.launch(intent, options)
        }

        //第二张卡片的点击事件
        binding.ivSecondCard.setOnClickListener {
            val intent = Intent(getContext(), FlipPage::class.java)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(binding.ivSecondCard, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as ComponentActivity,
                imagePair
            )

            // 启动活动并应用转场动画
            myActivityLauncher2.launch(intent, options)


        }

        ////第三张卡片的点击事件
        binding.ivThirdCard.setOnClickListener {
            val intent = Intent(getContext(), FlipPage::class.java)

            // 创建共享元素的 Pair 对象
            val imagePair = Pair<View, String>(binding.ivThirdCard, "transition_image")

            // 创建 ActivityOptionsCompat，并设置共享元素转场动画
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as ComponentActivity,
                imagePair
            )

            // 启动活动并应用转场动画
            myActivityLauncher3.launch(intent, options)
        }
    }
}