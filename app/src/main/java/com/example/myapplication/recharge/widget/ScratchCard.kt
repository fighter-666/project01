package com.example.myapplication.recharge.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.util.DensityUtils
import com.example.myapplication.R
import java.util.Random


class ScratchCard : View {
    private lateinit var mBitmapBackground: Bitmap
    private lateinit var mBitmapFront: Bitmap
    private lateinit var mCanvas: Canvas
    private lateinit var pathPaint: Paint
    private lateinit var paint: Paint
    private lateinit var path: Path
    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f
    var disX: Float = 0f
    var mBitmapFrontWidth: Float = 0f
    var w: Int = 0
    var h: Int = 0
    var mBitmapFrontHeight: Float = 0f
    private var showFullResult = false
    var scaleWidth: Float = 0f
    var scaleHeight: Float = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        pathPaint = Paint()
        paint = Paint()
        pathPaint.alpha = 0
        pathPaint.style = Paint.Style.STROKE
        pathPaint.strokeWidth = 50f
        pathPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN) //混合模式
        pathPaint.strokeJoin = Paint.Join.ROUND //线段之间连接处的样式
        pathPaint.strokeCap = Paint.Cap.ROUND //设置画笔的线冒样式
        path = Path()

        //生成随机事件
        val random = Random()
        val randomNumber = random.nextInt(2)
        val resourceId = if (randomNumber == 0) {
            R.drawable.scratch1
        } else {
            R.drawable.scratch2
        }
        mBitmapBackground = BitmapFactory.decodeResource(resources, resourceId)

        mBitmapFront = BitmapFactory.decodeResource(resources, R.drawable.scratch3)

        mCanvas = Canvas()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(mBitmapBackground, 0f, 0f, null)
        //绘制
        mBitmapFrontWidth = (mBitmapBackground.width * 0.03).toFloat()
        mBitmapFrontHeight = (mBitmapBackground.height * 0.03).toFloat()
        canvas.drawBitmap(mBitmapFront, mBitmapFrontWidth, mBitmapFrontHeight, paint)
        if (showFullResult) {
            // 绘制完整的刮奖结果
            paint.alpha = 0 // 设置透明
            canvas.drawBitmap(mBitmapFront, mBitmapFrontWidth, mBitmapFrontHeight, paint)
        } else {
            // 绘制刮动的路径线条
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取宽高
        w = MeasureSpec.getSize(widthMeasureSpec)
        h = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        //适配屏幕
        mBitmapBackground = getBitmap(mBitmapBackground, w, h)
        mBitmapFront = getBitmap(
            mBitmapFront, (mBitmapBackground.width * 0.95).toInt(),
            (mBitmapBackground.height * 0.92).toInt()
        )

        //设置绘图画布（Canvas）的目标位图为 mBitmapFront，接下来的绘制操作将直接作用于 mBitmapFront
        mCanvas.setBitmap(mBitmapFront)
        //mCanvas.drawColor(Color.parseColor("#FBECEB"))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //重置绘制路径
                path.reset()
                //使用path.moveTo()将原点移动到手指的触摸点
                path.moveTo(event.x - mBitmapFrontWidth, event.y - mBitmapFrontHeight) //原点移动至手指的触摸点
                startX = event.x
                startY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                //使用path.lineTo()方法将路径绘制到当前触摸点
                path.lineTo(event.x - mBitmapFrontWidth, event.y - mBitmapFrontHeight)
                endX = event.x
                endY = event.y

            }

            MotionEvent.ACTION_UP -> {

            }
        }
        performClick()

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        // Handle the click event here
        val bitmap: Bitmap = mBitmapFront // 从某处获取位图对象
        val w: Int = bitmap.width // 位图的宽度
        val h: Int = bitmap.height // 位图的高度
        val mPixels: IntArray = IntArray(w * h) // 创建一个整型数组来存储像素数据

        //获取一个位图（Bitmap）的像素，并计算刮除区域的面积
        bitmap.getPixels(mPixels, 0, w, 0, 0, w, h)
        var wipeArea = 0f
        val totalArea = w * h.toFloat()

        //循环遍历位图的每个像素
        for (i in 0 until w) {
            for (j in 0 until h) {
                //计算i + j * w来获取在mPixels中的索引
                val index = i + j * w

                //如果该索引处的像素值为0（即透明的像素）
                if (mPixels[index] == 0) {
                    // 当 wipeArea > 30 时，说明文字全部刮出来了
                    wipeArea++
                }
            }
        }

        // 计算刮开区域的百分比
        if (wipeArea > 0 && totalArea > 0) {
            val percent = (wipeArea * 100 / totalArea).toInt()
            // 处理刮开区域百分比的逻辑
            if (percent > 25) {
                showFullResult = true
            }
        }

        //上下冲突
        if ((endY - startY) < mBitmapBackground.height) {
            parent.requestDisallowInterceptTouchEvent(true)
        } else {
            parent.requestDisallowInterceptTouchEvent(false)
        }

        //左右冲突
        if ((endX - startX) < mBitmapBackground.width) {
            parent.requestDisallowInterceptTouchEvent(true)
        } else {
            parent.requestDisallowInterceptTouchEvent(false)
        }

        mCanvas.drawPath(path, pathPaint)
        invalidate()
        return true
    }

    fun getBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        // 获得图片的宽高
        val width = bm.width
        val height = bm.height
        // 计算缩放比例
        scaleWidth = newWidth.toFloat() / width
        scaleHeight = (newWidth.toFloat()) / 668 * 214.toFloat() / height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true)
    }
}