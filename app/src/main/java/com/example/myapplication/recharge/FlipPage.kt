package com.example.myapplication.recharge

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
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.myapplication.GetScreenUtils
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

class FlipPage : SlideRightBackActivity()  {
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

        //获取图片资源
        beam.setImageResource(R.drawable.ic_flip_card_ray)
        card.setImageResource(R.drawable.card1)
        bottom.setImageResource(R.drawable.cheer)

        //关闭动画页面
        closeicon.setOnClickListener {
            onBackPressed()
        }

        //获取屏幕宽度
        val imageWidth = GetScreenUtils.getScreenWidth(this)

        //动态设置底部动效的宽高
        val layoutParams1 = bottom.layoutParams
        val initialWidth = 720
        val initialHeight = 377

        layoutParams1.width = imageWidth
        val widthScale1 = layoutParams1.width.toFloat() / 720
        layoutParams1.height = (initialHeight * widthScale1).toInt()
        bottom.layoutParams = layoutParams1

        //贴脸的效果移除，我的测试机density是2.75
        val density = resources.displayMetrics.density
        val cameraDistance = density * 10000
        card.cameraDistance = cameraDistance

        //卡片缩放从100%放大到150%（缩放时间：0.2s）
        val beamScaleX0 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.000000000000000001f)
        val beamScaleY0 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.000000000000000001f)
        beamScaleX0.duration = 1
        beamScaleY0.duration = 1
        val scaleX = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f)
        val scaleY = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f)
        scaleX.duration = 400
        scaleY.duration = 400
        resetDurationScale(beamScaleX0)
        beamScaleX0.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                prise.visibility = View.GONE
                button.visibility = View.GONE
                hint.visibility = View.GONE
            }
        })

        //2、卡片旋转由0度转向+15度（旋转时间0.2s）
        //    光束出场从0%缩放至60%（缩放时间0.2s）
        val rotation = ObjectAnimator.ofFloat(card, View.ROTATION, 15f)
        rotation.duration = 200

        val beamScaleX = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX.duration = 200
        beamScaleY.duration = 200

        //卡片摇晃的时候，用户关闭弹窗，卡片要回到初始态，然后开始退场动画
        rotation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val intent2 = Intent().apply {
                    putExtra("result",R.drawable.card1 )
                }
                setResult(Activity.RESULT_OK, intent2)
            }
        })

        //3、卡片旋转由+15度转向0度（旋转时间0.2s）
        //   光束从60%缩放至50%（缩放时间0.2s）
        val rotation2 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation2.duration = 200

        val beamScaleX2 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY2 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX2.duration = 200
        beamScaleY2.duration = 200

        //4、卡片旋转由0度转向-15度（旋转时间0.2s）
        //   光束从50%缩放至60%（缩放时间0.2s）
        val rotation3 = ObjectAnimator.ofFloat(card, View.ROTATION, -15f)
        rotation3.duration = 200

        val beamScaleX3 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f)
        val beamScaleY3 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f)
        beamScaleX3.duration = 200
        beamScaleY3.duration = 200

        //5、卡片旋转由-15度转向0度（旋转时间0.2s）
        //   光束从60%缩放至50%（缩放时间0.2s）
        val rotation4 = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
        rotation4.duration = 200

        val beamScaleX4 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY4 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX4.duration = 200
        beamScaleY4.duration = 200

        //6、光束从50%缩放至60%（缩放时间0.2s）
        val beamScaleX5 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.5f)
        val beamScaleY5 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.5f)
        beamScaleX5.duration = 200
        beamScaleY5.duration = 200

        //7、卡片绕y轴旋转由0度旋转至270度（旋转时间0.4s）
        //   卡片缩放由150%放大至300%（缩放时间0.4s）
        //   光束消失透明度由100%变至0%（变化时间0.2s）
        val rotation6 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, 0f, 270f)
        rotation6.duration = 400
        rotation6.interpolator = LinearInterpolator()

        scaleX6 = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.5f, 3f)
        scaleY6 = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.5f, 3f)
        scaleX6.duration = 200
        scaleY6.duration = 200

        val alpha6 = ObjectAnimator.ofFloat(beam, View.ALPHA, 1f, 0f)
        alpha6.duration = 400

        //把抽到的卡片回传到上一个页面点击的那一项并显示出来
        rotation6.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 在rotation6结束后更换图片资源
                //生成一个随机的图片资源ID
                var randomImageRes = 0
                val imageArray = arrayOf(R.drawable.winner, R.drawable.card3)
                val random = Random()
                val index = random.nextInt(imageArray.size)
                randomImageRes = imageArray[index]

                //给对应图片添加相应的点击事件监听器
                if (randomImageRes == R.drawable.winner) {
                    // 选中了 R.drawable.winner
                    // 执行相应的逻辑
                    prise.visibility = View.VISIBLE
                    button.visibility = View.VISIBLE
                    hint.visibility = View.VISIBLE
                    //按钮的点击事件
                    button.setOnClickListener {
                        Toast.makeText(this@FlipPage, "点击了", Toast.LENGTH_SHORT).show()
                    }
                } else{
                    // 选中了 R.drawable.card3
                    // 执行相应的逻辑
                    // ...
                }

                //设置一个带有结果数据的Intent并将其作为结果返回给调用方
                val intent1 = Intent().apply {
                    putExtra("result",randomImageRes )
                }
                setResult(Activity.RESULT_OK, intent1)
                // 判断选中的是哪个资源

                // 设置到ImageView
                card.setImageResource(randomImageRes)
            }
        })

        //8、弹窗绕y轴翻转由-90度转至0度（旋转时间0.2s）
        //   光束出现透明度由0%变至100%（变化时间0.2s）
        //光束从60%缩放至100%
        val rotation7 = ObjectAnimator.ofFloat(card, View.ROTATION_Y, 270f, 360f)
        rotation7.duration = 400

        val alpha7 = ObjectAnimator.ofFloat(beam, View.ALPHA, 0f, 1f)
        alpha7.duration = 200

        val scaleX7 = ObjectAnimator.ofFloat(beam, View.SCALE_X, 0.6f, 1.5f)
        val scaleY7 = ObjectAnimator.ofFloat(beam, View.SCALE_Y, 0.6f, 1.5f)
        scaleX7.duration = 200
        scaleY7.duration = 200
        rotation7.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 在rotation结束后添加图片资源
                closeicon.setImageResource(R.drawable.closeicon)
            }
        })

// 底部动效出现动画
        rotation7.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                /*(bottom.drawable as? AnimatedImageDrawable)?.start()*/
                loadImage(bottom)
            }
        })

        //9、光束以4s一圈的速度进行旋转
        //   底部动效出现
        val rotation8 = ObjectAnimator.ofFloat(beam, View.ROTATION, 0f, 360f)
        rotation8.duration = 4000
        rotation8.repeatCount = ValueAnimator.INFINITE
        rotation8.interpolator = LinearInterpolator()

        combinedAnimatorSet = AnimatorSet().apply {
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
        combinedAnimatorSet.start()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // 在这里执行您的逻辑
        //卡片摇晃的时候，用户关闭弹窗，卡片要回到初始态，然后开始退场动画。
        if (combinedAnimatorSet.isRunning) {
            combinedAnimatorSet.cancel()
            val cardRotation = ObjectAnimator.ofFloat(card, View.ROTATION, 0f)
            cardRotation.duration = 200
            cardRotation.start()

            //使用协程让返回键延迟执行
            CoroutineScope(Dispatchers.Main).launch {
                delay(200)
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }

    }

    //让glide只加载一次gif图片
    //定义了一个options对象，用于配置Glide的选项。这里设置了图片的
    // 缩放方式为fitCenter()，跳过内存缓存（skipMemoryCache(true)），
    // 并将磁盘缓存策略设置为DiskCacheStrategy.DATA，表示只缓存原始数据。
    val options = RequestOptions()
        .fitCenter()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
    fun loadImage(imageView: ImageView) {
        Glide.with(getApplicationContext())
            .asGif()
            .load(R.drawable.cheer)
            .apply(options)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
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