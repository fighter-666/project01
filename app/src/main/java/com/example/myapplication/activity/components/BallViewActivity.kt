package com.example.myapplication.activity.components


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.view.BallView

class BallViewActivity : AppCompatActivity() {
    private lateinit var ballView: BallView
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball_view)

        ballView = findViewById<BallView>(R.id.ballView)
        val settingsButton = findViewById<Button>(R.id.buttonSettings)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsBallActivity::class.java)
            startActivity(intent)
        }
        // 恢复状态
        savedInstanceState?.let {
            ballView.ballX = it.getFloat("ballX", 100f)
            ballView.ballY = it.getFloat("ballY", 100f)
            ballView.setBallColor(it.getInt("ballColor", Color.BLUE))
            ballView.setBallSize(it.getFloat("ballSize", 50f))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 保存状态
        outState.putFloat("ballX", ballView.ballX)
        outState.putFloat("ballY", ballView.ballY)
        outState.putInt("ballColor", ballView.ballPaint.color)
        outState.putFloat("ballSize", ballView.ballRadius)


    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("BallViewSettings", Context.MODE_PRIVATE)
        val ballSize = sharedPref.getInt("ballSize", 50) // 默认大小为50
        val ballColor = sharedPref.getInt("ballColor", Color.BLUE) // 默认颜色为蓝色
        val gravityMode = sharedPref.getBoolean("gravityMode", false) // 默认关闭重力感应模式

        ballView.setBallSize(ballSize.toFloat())
        ballView.setBallColor(ballColor)
        ballView.setGravityMode(gravityMode)
    }

    override fun onPause() {
    super.onPause()
    ballView.saveState(this)
}
}