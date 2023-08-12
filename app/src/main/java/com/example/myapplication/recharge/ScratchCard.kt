package com.example.myapplication.recharge

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
import androidx.core.widget.NestedScrollView
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.R


class ScratchCard : View {
    private lateinit var mBitmapBackground: Bitmap
    private lateinit var mBitmapFront: Bitmap
    private var mCanvas: Canvas? = null
    private var pathPaint: Paint? = null
    private var paint: Paint? = null
    private var path: Path? = null
    private var mPaintText: Paint? = null
    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f
    private var shouldInterceptScroll = false
    private val scrollThreshold = 160

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
        pathPaint!!.alpha = 0
        pathPaint!!.style = Paint.Style.STROKE
        pathPaint!!.strokeWidth = 50f
        pathPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN) //混合模式
        pathPaint!!.strokeJoin = Paint.Join.ROUND //线段之间连接处的样式
        pathPaint!!.strokeCap = Paint.Cap.ROUND //设置画笔的线冒样式
        path = Path()
        mBitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.scratch1)
        mCanvas = Canvas()
        mPaintText = Paint()
        mPaintText!!.color = Color.WHITE
        mPaintText!!.textSize = 100f
        mPaintText!!.strokeWidth = 20f

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(mBitmapBackground, 0f, 0f, null)
        canvas.drawBitmap(mBitmapFront, 62f, 36f, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmapBackground = getBitmap(mBitmapBackground, w, h)
        mBitmapFront = Bitmap.createBitmap(
            mBitmapBackground.width - 124,

            mBitmapBackground.height - 72 , Bitmap.Config.ARGB_8888
        )
        mCanvas!!.setBitmap(mBitmapFront)
        drawText(mCanvas, w, h)
    }


    var quarterWidth = 84

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path!!.reset()
                path!!.moveTo(event.x, event.y) //原点移动至手指的触摸点
                startX = event.x
                startY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                path!!.lineTo(event.x, event.y)
                endX = event.x
                endY = event.y
            }

        }
        if ((endY - startY) < scrollThreshold) {
            shouldInterceptScroll = true
        } else {
            shouldInterceptScroll = false
        }
        /*if ((endX - startX) > quarterWidth) {
            // 滑动距离大于 quarterWidth
            // 在这里添加你的逻辑代码

        }*/
        mCanvas!!.drawPath(path!!, pathPaint!!)
        invalidate()

        if (shouldInterceptScroll) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return true
    }

    private fun drawText(canvas: Canvas?, mWidth: Int, mHeight: Int) {
        val text = " "
        canvas!!.drawColor(Color.parseColor("#FBECEB"))
        val fm = mPaintText!!.fontMetrics
        val mTxtWidth = mPaintText!!.measureText(text, 0, text.length).toInt()
        val mTxtHeight = Math.ceil((fm.descent - fm.ascent).toDouble()).toInt()
        val x = mWidth / 2 - mTxtWidth / 2 //文字在画布中的x坐标
        val y = mHeight / 2 + mTxtHeight / 4 //文字在画布中的y坐标
        canvas.drawText(text, x.toFloat(), y.toFloat(), mPaintText!!)
    }

    fun getBitmap(bm: Bitmap?, newWidth: Int, newHeight: Int): Bitmap {
        // 获得图片的宽高
        val width = bm!!.width
        val height = bm.height
        // 计算缩放比例
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true)
    }
}