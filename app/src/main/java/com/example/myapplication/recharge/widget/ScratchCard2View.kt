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
import com.example.myapplication.R


class ScratchCard2View(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val mSrcText: Bitmap
    private val mSrcBitmap: Bitmap
    private val mDstBitmap: Bitmap
    private val mPaint: Paint
    private val mPath: Path
    private val matrix: Matrix
    private val matrix2: Matrix
    private val c: Canvas
    private var mStartX = 0f
    private var mStartY = 0f

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 45f
        mSrcText = BitmapFactory.decodeResource(resources, R.drawable.scratch1)
        mSrcBitmap = BitmapFactory.decodeResource(resources, R.drawable.scratch3)

        mDstBitmap =
            Bitmap.createBitmap(mSrcBitmap.width, mSrcBitmap.height, Bitmap.Config.ARGB_8888)
        mPath = Path()

        matrix = Matrix()
        matrix2 = Matrix()
        c = Canvas(mDstBitmap)

        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画最终呈现的图
        val scaleX = 0.5f
        val scaleY = 0.5f


        matrix.setScale(scaleX, scaleY)

        canvas.drawBitmap(mSrcText, matrix, mPaint)
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        //把手指轨迹画到画布上

        c.drawPath(mPath, mPaint)
        //利用SRC_OUT绘制原图
        canvas.drawBitmap(mDstBitmap, 0f, 0f, mPaint)


        val scaleX2 = 0.5f
        val scaleY2 = 0.5f


        matrix2.setScale(scaleX2, scaleY2)

        canvas.drawBitmap(mSrcBitmap, matrix2, mPaint)
        //canvas.drawBitmap(mSrcBitmap, 0f, 0f, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(layerId)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x, event.y)
                mStartX = event.x
                mStartY = event.y
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val endX = (mStartX + event.x) / 2
                val endY = (mStartY + event.y) / 2
                mPath.quadTo(mStartX, mStartY, endX, endY)
                mStartX = event.x
                mStartY = event.y
            }

            else -> {}
        }
        postInvalidate()
        performClick()
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        // 处理点击事件的逻辑
        super.performClick() // 调用父类的 performClick 方法，包含默认的点击行为
        return true
    }
}