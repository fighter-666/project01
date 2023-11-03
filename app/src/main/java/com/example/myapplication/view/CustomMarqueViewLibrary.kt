package com.example.myapplication.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomMarqueViewLibraryBinding
import com.gongwen.marqueen.SimpleMF


class CustomMarqueViewLibrary : ConstraintLayout {
    var leftTitle: SimpleMF<String>? = null
        set(value) {
            field = value
            // 在此处进行其他操作，例如更新视图
        }
    var centerTitle: SimpleMF<String>? = null
        set(value) {
            field = value
            // 在此处进行其他操作，例如更新视图
        }
    var site: SimpleMF<String>? = null
        set(value) {
            field = value
            // 在此处进行其他操作，例如更新视图
        }
    var rightTitle: SimpleMF<String>? = null
        set(value) {
            field = value
            // 在此处进行其他操作，例如更新视图
        }

    /**
     * 这个构造方法是在代码中new的时候调用的
     * @param context
     */
    constructor(context: Context?) : super(context!!)

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
    )

    init {

    }


    private fun initView(context: Context, attrs: AttributeSet) {

        //获取子控件
        val binding= CustomMarqueViewLibraryBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMarqueViewLibrary)
        val leftTitleResId = typedArray.getResourceId(R.styleable.CustomMarqueViewLibrary_leftTitle, 0)
        if (leftTitleResId != 0) {
            val marqueeFactory = SimpleMF<String>(context)
            marqueeFactory.setData(resources.getStringArray(leftTitleResId).toMutableList())
            leftTitle = marqueeFactory
        }


        //leftTitle = typedArray.getString(R.styleable.CustomMarqueViewLibrary_leftTitle)
        //centerTitle = typedArray.getString(R.styleable.CustomMarqueViewLibrary_centerTitle)
        //val site = typedArray.getString(R.styleable.CustomMarqueViewLibrary_site)
        //val rightTitle = typedArray.getString(R.styleable.CustomMarqueViewLibrary_rightTitle)



        typedArray.recycle()



        binding.leftTitle.setMarqueeFactory(leftTitle)
       /* binding.centerTitle.setMarqueeFactory(centerTitle)
        binding.site.setMarqueeFactory(site)
        binding.rightTitle.setMarqueeFactory(rightTitle)
*/




    }
}