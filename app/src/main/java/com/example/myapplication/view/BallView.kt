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

// BallView 是自定义视图类，用于显示和控制小球。
class BallView(context: Context, attrs: AttributeSet? = null) : View(context, attrs),
    SensorEventListener {
    var ballX = 100f // 小球的 X 坐标
    var ballY = 100f // 小球的 Y 坐标
    var ballRadius = 50f // 小球的半径
    var ballPaint = Paint().apply { color = Color.BLUE } // 小球的画笔，初始颜色为蓝色
    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager // 传感器管理器
    private var lastUpdate = System.currentTimeMillis() // 上次更新时间
    private var isGravityModeEnabled = false // 是否启用重力感应模式

    init {
        // 在初始化时，从 SharedPreferences 读取小球的状态
        val sharedPref = context.getSharedPreferences("BallViewPrefs", Context.MODE_PRIVATE)
        ballX = sharedPref.getFloat("ballX", 100f)
        ballY = sharedPref.getFloat("ballY", 100f)
        ballRadius = sharedPref.getFloat("ballRadius", 50f)
        ballPaint.color = sharedPref.getInt("ballColor", Color.BLUE)
        isGravityModeEnabled = sharedPref.getBoolean("isGravityModeEnabled", false)

        // 如果重力感应模式启用，注册传感器监听器
        if (isGravityModeEnabled) {
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    // 设置小球颜色
    fun setBallColor(color: Int) {
        ballPaint.color = color
        invalidate() // 重绘视图以更新颜色
    }

    // 设置小球大小
    fun setBallSize(radius: Float) {
        ballRadius = radius
        invalidate() // 重绘视图以更新大小
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 在画布上绘制小球
        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint)
    }

    /*event.x 和 event.y 获取的是触摸事件发生时的坐标位置。
min(event.x, width - ballRadius) 确保了小球的中心点 x 坐标不会超过视图的宽度减去小球半径的值。
这是为了防止小球的一部分超出视图的右边界。如果触摸事件的 x 坐标大于 width - ballRadius，
则小球的 x 坐标会被设置为 width - ballRadius。

max(ballRadius, ...) 确保了小球的中心点 x 坐标不会小于小球的半径。这是为了防止小球的一部分超出视图的左边界。
如果上一步计算的结果小于 ballRadius，则小球的 x 坐标会被设置为 ballRadius。
同样的逻辑也适用于 y 坐标，min(event.y, height - ballRadius) 确保小球不会超出视图的下边界，
而 max(ballRadius, ...) 确保小球不会超出视图的上边界。

总之，这两行代码结合使用 max 和 min 函数，有效地限制了小球的位置，确保它始终在视图的边界内移动，
即使在触摸事件导致的位置更新时也是如此。*/

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isGravityModeEnabled) {
            // 如果重力感应模式未启用，允许通过触摸移动小球
            ballX = max(ballRadius, min(event.x, width - ballRadius))
            ballY = max(ballRadius, min(event.y, height - ballRadius))
            invalidate() // 更新视图
        }
        return true
    }



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // 此方法在传感器精度改变时调用，此处不需要实现
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //个条件检查确保当前处理的是加速度传感器的事件，并且重力感应模式已经被启用。
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER && isGravityModeEnabled) {
            // 在重力感应模式下，根据加速度传感器的数据更新小球位置
            val curTime = System.currentTimeMillis()
            //这个条件判断自上次更新以来是否已经过去了至少100毫秒。这是为了限制更新频率，避免过于频繁的更新导致的性能问题。
            if ((curTime - lastUpdate) > 100) {
                /*从加速度传感器事件中获取x轴和y轴的加速度值。
                在Android设备中，加速度传感器返回的是一个三元素的数组，分别代表x轴、y轴和z轴的加速度。*/
                val x = event.values[0]
                val y = event.values[1]
                //根据加速度值更新小球的位置。这里乘以2是为了增加移动的速度，使效果更明显。
                // 注意y轴的加速度用来更新ballY时是加上的，因为屏幕坐标系中向下是y轴的正方向。
                ballX -= x * 2
                ballY += y * 2

                // 确保小球不会移出屏幕
                ballX = max(ballRadius, min(ballX, width - ballRadius))
                ballY = max(ballRadius, min(ballY, height - ballRadius))

                invalidate() // 更新视图
                lastUpdate = curTime
            }
        }
    }

    // 设置重力感应模式
    fun setGravityMode(enabled: Boolean) {
        isGravityModeEnabled = enabled
        if (enabled) {
            // 启用重力感应，注册传感器监听器
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        } else {
            // 禁用重力感应，取消注册传感器监听器
            sensorManager.unregisterListener(this)
        }
    }

    // 保存小球的当前状态到 SharedPreferences
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