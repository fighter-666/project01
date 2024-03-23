package com.example.myapplication.activity.components


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityBallViewBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar

// BallViewActivity 是主活动类，负责显示小球和设置按钮。
class BallViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBallViewBinding // 使用 View Binding 来访问布局中的视图

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBallViewBinding.inflate(layoutInflater) // 初始化 View Binding
        setContentView(binding.root) // 设置布局内容视图

        // 配置沉浸式状态栏
        ImmersionBar.with(this@BallViewActivity).transparentStatusBar()
            .titleBar(binding.btSettings)
            .hideBar(BarHide.FLAG_HIDE_BAR)
            .init()

        // 设置按钮点击事件，跳转到设置页面
        binding.btSettings.setOnClickListener {
            val intent = Intent(this@BallViewActivity, SettingsBallActivity::class.java)
            startActivity(intent)
        }

        // 恢复保存的小球状态（如果有）
        savedInstanceState?.let {
            binding.run {
                ballView.ballX = it.getFloat("ballX", 100f)
                ballView.ballY = it.getFloat("ballY", 100f)
                ballView.setBallColor(it.getInt("ballColor", Color.BLUE))
                ballView.setBallSize(it.getFloat("ballSize", 50f))
            }
        }
    }

    // 保存小球的当前状态
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.run {
            outState.putFloat("ballX", ballView.ballX)
            outState.putFloat("ballY", ballView.ballY)
            outState.putInt("ballColor", ballView.ballPaint.color)
            outState.putFloat("ballSize", ballView.ballRadius)
        }
    }

    // 在 onResume 方法中，从 SharedPreferences 读取设置并应用到小球上
    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("BallViewSettings", Context.MODE_PRIVATE)
        val ballSize = sharedPref.getInt("ballSize", 50)
        val ballColor = sharedPref.getInt("ballColor", Color.BLUE)
        val gravityMode = sharedPref.getBoolean("gravityMode", false)

        binding.run {
            ballView.setBallSize(ballSize.toFloat())
            ballView.setBallColor(ballColor)
            ballView.setGravityMode(gravityMode)
        }
    }

    // 在 onPause 方法中，保存小球的当前状态到 SharedPreferences
    override fun onPause() {
        super.onPause()
        binding.ballView.saveState(this)
    }
}