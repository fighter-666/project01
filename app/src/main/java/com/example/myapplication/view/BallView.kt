package com.example.myapplication.view


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Float.max
import java.lang.Float.min

class BallView(context: Context, attrs: AttributeSet? = null) : View(context, attrs),
    SensorEventListener {
    var ballX = 100f
    var ballY = 100f
    var ballRadius = 50f
    var ballPaint = Paint().apply {
        color = Color.BLUE
    }
    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var lastUpdate = System.currentTimeMillis()
    private var isGravityModeEnabled = false

    init {
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )
        val sharedPref = context.getSharedPreferences("BallViewPrefs", Context.MODE_PRIVATE)
        ballX = sharedPref.getFloat("ballX", 100f) // 默认值作为参数
        ballY = sharedPref.getFloat("ballY", 100f)
        ballRadius = sharedPref.getFloat("ballRadius", 50f)
        ballPaint.color = sharedPref.getInt("ballColor", Color.BLUE)
        isGravityModeEnabled = sharedPref.getBoolean("isGravityModeEnabled", false)

        if (isGravityModeEnabled) {
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    fun setBallColor(color: Int) {
        ballPaint.color = color
        invalidate()
    }

    fun setBallSize(radius: Float) {
        ballRadius = radius
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isGravityModeEnabled) {
            ballX = event.x
            ballY = event.y
            invalidate()
        }
        return true
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // 不需要实现
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER && isGravityModeEnabled) {
            val curTime = System.currentTimeMillis()
            if ((curTime - lastUpdate) > 100) {
                val x = event.values[0]
                val y = event.values[1]
                ballX -= x * 2
                ballY += y * 2
                // 确保小球不会移出屏幕
                ballX = max(0f, min(ballX, width.toFloat() - ballRadius))
                ballY = max(0f, min(ballY, height.toFloat() - ballRadius))
                invalidate()
                lastUpdate = curTime
            }
        }
    }

    fun setGravityMode(enabled: Boolean) {
        isGravityModeEnabled = enabled
        if (enabled) {
            // 注册传感器监听器，启用重力感应
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        } else {
            // 取消注册传感器监听器，关闭重力感应
            sensorManager.unregisterListener(this)
        }
    }
    fun saveState(context: Context) {
        val sharedPref = context.getSharedPreferences("BallViewPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putFloat("ballX", ballX)
            putFloat("ballY", ballY)
            putFloat("ballRadius", ballRadius)
            putInt("ballColor", ballPaint.color)
            putBoolean("isGravityModeEnabled", isGravityModeEnabled)
            apply()
        }
    }

}