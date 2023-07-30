package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView

class FlipPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_Transparent) // 设置透明主题
        setContentView(R.layout.activity_flip_page)

        val card = findViewById<ImageView>(R.id.card)

        val beam = findViewById<ImageView>(R.id.beam)

        val bottom = findViewById<ImageView>(R.id.bottom)

        val beamScaleX0 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.000000000000000001f)
        val beamScaleY0 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.000000000000000001f)
        beamScaleX0.duration = 400
        beamScaleY0.duration = 400
        val scaleX = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f)
        val scaleY = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f)
        scaleX.duration = 400
        scaleY.duration = 400


        val rotation = ObjectAnimator.ofFloat(card, View.ROTATION, 15f)
        rotation.duration = 600

        val beamScaleX = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX.duration = 600
        beamScaleY.duration = 600

        val rotation2 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation2.duration = 800

        val beamScaleX2 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY2 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX2.duration = 800
        beamScaleY2.duration = 800

        val rotation3 = ObjectAnimator.ofFloat(card, View.ROTATION, -15f)
        rotation3.duration = 1000

        val beamScaleX3 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY3 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX3.duration = 1000
        beamScaleY3.duration = 1000

        val rotation4 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation4.duration = 1200

        val beamScaleX4 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY4 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX4.duration = 1200
        beamScaleY4.duration = 1200

        val beamScaleX5 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY5 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX5.duration = 1400
        beamScaleY5.duration = 1400

        val rotation6 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, 0f, 270f)
        rotation6.duration = 1800

        val scaleX6 = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f, 2.2f)
        val scaleY6 = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f, 2.2f)
        scaleX6.duration = 1800
        scaleY6.duration = 1800

        val alpha6 = ObjectAnimator.ofFloat(beam, View.ALPHA, 1f, 0f)
        alpha6.duration = 1600


        val rotation7 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, -90f, 0f)
        rotation7.duration = 200

        val alpha7 = ObjectAnimator.ofFloat(beam, View.ALPHA, 0f, 1f)
        alpha7.duration = 200

        val scaleX7 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f, 1f)
        val scaleY7 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f, 1f)
        scaleX7.duration = 200
        scaleY7.duration = 200

        rotation7.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 在rotation7结束后更换图片资源
                card.setImageResource(R.drawable.card2)
            }
        })

        // 光束旋转动画
        val rotation8 = ObjectAnimator.ofFloat(beam, View.ROTATION, 0f, 360f)
        rotation8.duration = 4000
        rotation8.repeatCount = ValueAnimator.INFINITE
        rotation8.interpolator = LinearInterpolator()

// 底部动效出现动画
        val bottomAnim8 = ObjectAnimator.ofFloat(bottom, View.ALPHA, 0f, 1f)
        bottomAnim8.duration = 500



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
            playTogether(rotation8, bottomAnim8)
            playSequentially(scaleX, rotation, rotation2, rotation3, rotation4,beamScaleX5, rotation6, rotation7, rotation8)
        }


        combinedAnimatorSet.start()
    }
}