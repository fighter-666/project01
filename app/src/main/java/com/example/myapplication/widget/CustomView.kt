package com.example.myapplication.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.View


/**
 * Created by Alex Pang on 2016/8/20.
 * 自定义View，使用PorterDuff.Mode验证图像合成效果
 */
class CustomView(context: Context?) : View(context) {

    private  var paint: Paint
    private  var path: Path
    private  var rectf: RectF
    private  var rectf2: RectF

    init {
        //创建了一个 Paint 对象，并设置了抗锯齿标志（ANTI_ALIAS_FLAG）
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        path = Path()
        rectf = RectF(200f, 1200f, 400f, 1400f)
        rectf2 = RectF(400f, 1200f, 600f, 1400f)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.setAntiAlias(true)
        //canvas.drawColor(Color.YELLOW)

        //画圆
        canvas.drawCircle(200f,200f,100f,paint)

        //画线模式的圆
        paint.style=Paint.Style.STROKE
        paint.strokeWidth = 20f
        canvas.drawCircle(400f,200f,100f,paint)

        //填充模式的圆
        paint.style = Paint.Style.FILL
        paint.setColor(Color.BLUE)
        canvas.drawCircle(200f,400f,100f,paint)

        //画矩形
        canvas.drawRect(300f,300f,500f,500f,paint)

        //线条断电的粗细和形状
        paint.strokeWidth = 40f
        paint.strokeCap=Paint.Cap.ROUND     //圆形
        canvas.drawPoint(550f,200f,paint)
        paint.strokeCap=Paint.Cap.SQUARE    //方形
        canvas.drawPoint(650f,200f,paint)

        //画椭圆
        paint.strokeWidth = 20f
        canvas.drawOval(500f,300f,700f,400f,paint)
        paint.style = Paint.Style.STROKE
        canvas.drawOval(500f,400f,700f,500f,paint)

        //画线
        canvas.drawLine(700f,100f,900f,300f,paint)

        //画圆角矩形
        canvas.drawRoundRect(700f,300f,900f,500f,50f,50f,paint)

        //绘制弧形或扇形
        paint.style = Paint.Style.FILL
        canvas.drawArc(200f,500f,800f,1000f,-110f,100f,true,paint)
        canvas.drawArc(200f,500f,800f,1000f,20f,140f,false,paint)
        paint.style = Paint.Style.STROKE
        canvas.drawArc(200f,500f,800f,1000f,180f,60f,false,paint)

        //画心形
        paint.style = Paint.Style.FILL
        paint.setColor(Color.RED)
        path.addArc(rectf, -225f, 225f)
        path.arcTo(rectf2, -180f, 225f, false)
        path.lineTo(400f, 1542f)
        canvas.drawPath(path,paint)

        // 假设 histogramData 是直方图数据，包含每个区间的频次/值
        val histogramData = intArrayOf(10, 20, 15, 30, 25, 140)

// 计算每个柱状图之间的间距和宽度
        val barSpacing = 20f
        val barWidth = (width - (histogramData.size + 1) * barSpacing) / histogramData.size.toFloat()

        val maxHeight = histogramData.maxOrNull()?.toFloat() ?: 0f

// 设置画笔样式和颜色
            paint.style = Paint.Style.FILL
            paint.color = Color.BLUE

        for (i in histogramData.indices) {
            val left = barSpacing + (barSpacing + barWidth) * i
            val right = left + barWidth
            val bottom = height.toFloat()
            val top = bottom - (histogramData[i].toFloat() / maxHeight) * height

            // 使用 drawRect 方法绘制柱状图
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }
}