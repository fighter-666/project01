package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFlipPageBinding
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Method
import java.util.Random

/**
 * 重置ValueAnimator动画时长 防止部分手机修改动画时长导致动画不执行
 *
 * @param valueAnimator 要重置时长的ValueAnimator对象
 */
@SuppressLint("DiscouragedPrivateApi")
fun resetDurationScale(valueAnimator: ValueAnimator) {
    try {
        val method: Method = ValueAnimator::class.java.getDeclaredMethod("setDurationScale", Float::class.javaPrimitiveType)
        method.isAccessible = true
        method.invoke(valueAnimator, 1f)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class Return : AppCompatActivity()  {
    private lateinit var binding: ActivityFlipPageBinding
    private lateinit var card: ImageView
    private lateinit var closeicon: ImageView
    private lateinit var prise: ImageView
    private lateinit var button: ImageView
    private lateinit var hint: ImageView
    private lateinit var beam: ImageView
    private lateinit var bottom: ImageView
    private lateinit var combinedAnimatorSet: AnimatorSet
    private lateinit var scaleX6: ObjectAnimator
    private lateinit var scaleY6: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlipPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init();

        card = binding.card
        closeicon = binding.card3
        beam = binding.beam
        prise = binding.card4
        button = binding.card5
        hint = binding.card6
        bottom = binding.bottom

        beam.setImageResource(R.drawable.ic_flip_card_ray)
        card.setImageResource(R.drawable.card1)
        bottom.setImageResource(R.drawable.cheer)

        prise.visibility = View.GONE
        button.visibility = View.GONE
        hint.visibility = View.GONE



        closeicon.setOnClickListener {
            onBackPressed()
        }









    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

            super.onBackPressed()


    }






}