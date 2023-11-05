package com.example.myapplication.activity.components

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.example.myapplication.data.ComplexItemEntity
import com.example.myapplication.databinding.ActivityVariousTextviewBinding
import com.example.myapplication.widget.ComplexViewHelper
import com.example.myapplication.widget.ComplexViewMF
import com.example.myapplication.widget.MyClickableSpan
import com.gongwen.marqueen.MarqueeFactory
import com.gongwen.marqueen.MarqueeView
import com.gongwen.marqueen.SimpleMF
import com.gongwen.marqueen.SimpleMarqueeView
import com.gyf.immersionbar.ImmersionBar
import java.util.Arrays

fun setMarqueeViewData(context: Context, binding: ActivityVariousTextviewBinding, complexDatas: List<ComplexItemEntity>) {
    val marqueeFactory = ComplexViewMF(context)
    marqueeFactory.data = complexDatas
    binding.marqueeView4.setMarqueeFactory(marqueeFactory)
    binding.marqueeView4.startFlipping()
}
class VariousTextviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVariousTextviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVariousTextviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init()

        //设置文字的前景色为淡蓝色
        val spannableString = SpannableString("设置文字的前景色为淡蓝色")
        val colorSpan = ForegroundColorSpan(Color.parseColor("#9999EE"))
        spannableString.setSpan(
            colorSpan,
            9,
            spannableString.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText.setText(spannableString)

        //设置文字的背景色为淡绿色
        val spannableString2 = SpannableString("设置文字的背景色为淡绿色")
        val colorSpan1 = BackgroundColorSpan(Color.parseColor("#AC00FF30"))
        spannableString2.setSpan(
            colorSpan1,
            9,
            spannableString2.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText2.setText(spannableString2)

        //调整字体大小
        val spannableString3 = SpannableString("万丈高楼平地起")
        val sizeSpan = RelativeSizeSpan(1.2f)
        val sizeSpan2 = RelativeSizeSpan(1.4f)
        val sizeSpan3 = RelativeSizeSpan(1.6f)
        val sizeSpan4 = RelativeSizeSpan(1.8f)
        val sizeSpan5 = RelativeSizeSpan(1.6f)
        val sizeSpan6 = RelativeSizeSpan(1.4f)
        val sizeSpan7 = RelativeSizeSpan(1.2f)
        spannableString3.setSpan(sizeSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan2, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan3, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan4, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan5, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan6, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString3.setSpan(sizeSpan7, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.tvText3.setText(spannableString3)

        //为文字设置删除线
        val spannableString4 = SpannableString("为文字设置删除线")
        val strikethroughSpan = StrikethroughSpan()
        spannableString4.setSpan(
            strikethroughSpan,
            5,
            spannableString4.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText4.setText(spannableString4)

        //为文字设置下划线
        val spannableString5 = SpannableString("为文字设置下划线")
        val underlineSpan = UnderlineSpan()
        spannableString5.setSpan(
            underlineSpan,
            5,
            spannableString5.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText5.setText(spannableString5)

        //为文字设置上标
        val spannableString6 = SpannableString("为文字设置上标")
        val superscriptSpan = SuperscriptSpan()
        spannableString6.setSpan(
            superscriptSpan,
            5,
            spannableString6.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText6.setText(spannableString6)

        //为文字设置下标
        val spannableString6Copy = SpannableString("为文字设置下标")
        val subscriptSpan = SubscriptSpan()
        spannableString6Copy.setSpan(
            subscriptSpan,
            5,
            spannableString6Copy.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText6Copy.setText(spannableString6Copy)

        //为文字设置粗体、斜体风格
        val spannableString7 = SpannableString("为文字设置粗体、斜体风格")
        val styleSpan_B = StyleSpan(Typeface.BOLD)
        val styleSpan_i = StyleSpan(Typeface.ITALIC)
        spannableString7.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString7.setSpan(styleSpan_i, 7, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.tvText7.setText(spannableString7)

        //在文本中添加表情（表情）
        val spannableString8 = SpannableString("在文本中添加表情（表情）")
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.beans, null)
        drawable?.setBounds(0, 0, 82, 82)
        val imageSpan = drawable?.let { ImageSpan(it) }
        spannableString8.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.tvText8.setText(spannableString8)

        //在文字设置点击事件
        val spannableString9 = SpannableString("在文字设置点击事件")
        //val clickableSpan = MyClickableSpan("http://www.jianshu.com/users/dbae0ac95c78", this)
        val clickableSpan = URLSpan("http://www.jianshu.com/users/dbae0ac95c78")
        spannableString9.setSpan(
            clickableSpan,
            5,
            spannableString9.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText9.setMovementMethod(LinkMovementMethod.getInstance())
        binding.tvText9.setHighlightColor(Color.parseColor("#36969696"))
        binding.tvText9.setText(spannableString9)

        //在文字设置点击事件
        val spannableString10 = SpannableString("在文字设置点击事件")
        val clickableSpan10 = MyClickableSpan("http://www.jianshu.com/users/dbae0ac95c78", this)
        spannableString10.setSpan(
            clickableSpan10,
            5,
            spannableString10.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvText10.setMovementMethod(LinkMovementMethod.getInstance())
        binding.tvText10.setHighlightColor(Color.parseColor("#36969696"))
        binding.tvText10.setText(spannableString10)

        val datas = Arrays.asList(
            "《赋得古原草送别》",
            "离离原上草，一岁一枯荣。",
            "野火烧不尽，春风吹又生。",
            "远芳侵古道，晴翠接荒城。",
            "又送王孙去，萋萋满别情。"
        )
//SimpleMarqueeView<T>，SimpleMF<T>：泛型T指定其填充的数据类型，比如String，Spanned等
//SimpleMarqueeView<T>，SimpleMF<T>：泛型T指定其填充的数据类型，比如String，Spanned等
        val marqueeView: SimpleMarqueeView<String> =
            findViewById(R.id.simpleMarqueeView)
        val marqueeFactory0: SimpleMF<String?> = SimpleMF<String?>(this)
        marqueeFactory0.setData(datas)
        marqueeView.setMarqueeFactory(marqueeFactory0)
        marqueeView.startFlipping()

        //initMarqueeView()

        val complexDatas: MutableList<ComplexItemEntity> = ArrayList()
        for (i in 0..4) {
            complexDatas.add(ComplexItemEntity("标题 $i", "副标题 $i", "时间 $i", "内容 $i"))
        }


     /*   val marqueeFactory = ComplexViewMF(this)
        marqueeFactory.data = complexDatas
        binding.marqueeView4.setMarqueeFactory(marqueeFactory)
        binding.marqueeView4.startFlipping()*/

        //setMarqueeViewData(this ,binding, complexDatas)

      /*  val complexDatas: MutableList<ComplexItemEntity> = ArrayList()
        for (i in 0..4) {
            complexDatas.add(ComplexItemEntity("标题 $i", "副标题 $i", "时间 $i", "内容 $i"))
        }*/

/*        val complexViewHelper = ComplexViewHelper(binding.marqueeView4 as MarqueeView<LinearLayout, ComplexItemEntity>)
        complexViewHelper.setComplexData(complexDatas)*/

       ComplexViewHelper(binding.marqueeView4 as MarqueeView<LinearLayout, ComplexItemEntity>)
       .setComplexData(complexDatas)



 /*       marqueeView4.setOnItemClickListener(object :
            OnItemClickListener<RelativeLayout?, ComplexItemEntity?>() {
            fun onItemClickListener(
                mView: RelativeLayout?,
                mData: ComplexItemEntity?,
                mPosition: Int,
            ) {
                Toast.makeText(
                    this@MainActivity,
                    String.format("mPosition:%s,mData:%s,mView:%s,.", mPosition, mData, mView),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })*/


    }

    private fun initMarqueeView() {
        val complexDatas: MutableList<ComplexItemEntity> = ArrayList()
        for (i in 0..4) {
            complexDatas.add(ComplexItemEntity("标题 $i", "副标题 $i", "时间 $i", "内容 $i"))
        }
        val marqueeFactory: MarqueeFactory<LinearLayout, ComplexItemEntity> =
            ComplexViewMF(this)

        marqueeFactory.setData(complexDatas)
        binding.marqueeView4.setInAndOutAnim(R.anim.in_bottom,R.anim.out_top)
        binding.marqueeView4.setMarqueeFactory(marqueeFactory)
        binding.marqueeView4.startFlipping()
    }

}