package com.example.myapplication.recharge

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimatedImageDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.components.RechargePage
import com.gyf.immersionbar.ImmersionBar


class FlipPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Transparent) // 设置透明主题
        setContentView(R.layout.activity_flip_page)
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init();

        val frameLayout = findViewById<FrameLayout>(R.id.fl)
        frameLayout.setOnClickListener {
            val intent = Intent(this,RechargePage::class.java)
            startActivity(intent)
        }

        val card = findViewById<ImageView>(R.id.card)

        val beam = findViewById<ImageView>(R.id.beam)

        val bottom = findViewById<ImageView>(R.id.bottom)
        beam.setImageResource(R.drawable.ic_flip_card_ray)
        card.setImageResource(R.drawable.card1)
        bottom.setImageResource(R.drawable.cheer)


        val beamScaleX0 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.000000000000000001f)
        val beamScaleY0 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.000000000000000001f)
        beamScaleX0.duration = 1
        beamScaleY0.duration = 1
        val scaleX = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f)
        val scaleY = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f)
        scaleX.duration = 200
        scaleY.duration = 200


        val rotation = ObjectAnimator.ofFloat(card, View.ROTATION, 15f)
        rotation.duration = 200

        val beamScaleX = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX.duration = 200
        beamScaleY.duration = 200

        val rotation2 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation2.duration = 200

        val beamScaleX2 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY2 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX2.duration = 200
        beamScaleY2.duration = 200

        val rotation3 = ObjectAnimator.ofFloat(card, View.ROTATION, -15f)
        rotation3.duration = 200

        val beamScaleX3 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY3 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX3.duration = 200
        beamScaleY3.duration = 200

        val rotation4 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation4.duration = 200

        val beamScaleX4 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY4 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX4.duration = 200
        beamScaleY4.duration = 200

        val beamScaleX5 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY5 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX5.duration = 200
        beamScaleY5.duration = 200

        val rotation6 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, 0f, 270f)
        rotation6.duration = 2000
        rotation6.interpolator = LinearInterpolator()

        val scaleX6 = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f, 3f)
        val scaleY6 = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f, 3f)
        scaleX6.duration = 200
        scaleY6.duration = 200

        val alpha6 = ObjectAnimator.ofFloat(beam, View.ALPHA, 1f, 0f)
        alpha6.duration = 400
        rotation6.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 在rotation6结束后更换图片资源
                card.setImageResource(R.drawable.card2)
            }
        })

        //我的测试机density是2.75
        val density = resources.displayMetrics.density
        val cameraDistance = density * 10000
        card.cameraDistance = cameraDistance


        val rotation7 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, 270f, 360f)
        rotation7.duration = 400

        val alpha7 = ObjectAnimator.ofFloat(beam, View.ALPHA, 0f, 1f)
        alpha7.duration = 200

        val scaleX7 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f, 1f)
        val scaleY7 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f, 1f)
        scaleX7.duration = 200
        scaleY7.duration = 200



        // 光束旋转动画
        val rotation8 = ObjectAnimator.ofFloat(beam, View.ROTATION, 0f, 360f)
        rotation8.duration = 4000
        rotation8.repeatCount = ValueAnimator.INFINITE
        rotation8.interpolator = LinearInterpolator()


// 底部动效出现动画
        rotation7.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                (bottom.drawable as? AnimatedImageDrawable)?.start()
            }
        })



        val cardAnimatorSet = AnimatorSet().apply {

        }

        val combinedAnimatorSet = AnimatorSet().apply {
            playTogether(scaleX, scaleY, beamScaleX0, beamScaleY0)
            playTogether(rotation, beamScaleX, beamScaleY)
            playTogether(rotation2, beamScaleX2, beamScaleY2)
            playTogether(rotation3, beamScaleX3, beamScaleY3)
            playTogether(rotation4, beamScaleX4, beamScaleY4)
            playTogether(beamScaleX5, beamScaleY5)
            playTogether(rotation6, scaleX6, scaleY6, alpha6)
            playTogether(rotation7, alpha7, scaleX7, scaleY7)
            playTogether(rotation8)
            playSequentially(scaleX, rotation, rotation2, rotation3, rotation4,beamScaleX5, rotation6, rotation7, rotation8)
        }
        card.setOnClickListener {
            AnimatorSet().apply {
                if (combinedAnimatorSet.isRunning) {
                    combinedAnimatorSet.end()
                }
                playSequentially(rotation7, rotation8)
                start()
            }
        }

        combinedAnimatorSet.start()

    }

    val options = RequestOptions()
        .fitCenter()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
    fun loadImage(imageView: ImageView) {
        Glide.with(this)
            .asGif()
            .load(R.drawable.cheer)
            .apply(options)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    // 设置只播放一次
                    resource?.setLoopCount(1)
                    return false
                }
            })
            .into(imageView)
    }
}