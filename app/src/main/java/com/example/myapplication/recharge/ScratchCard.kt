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
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.GetScreenUtils
import com.example.myapplication.R
import java.util.Random


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
    var disX: Float = 0f
    var disY: Float = 0f
    var area: Float = 0f
    var area2: Float = 0f
    var sum: Float = 0f
    private lateinit var mPixels: IntArray
    private var shouldInterceptScroll = false
    private var showFullResult = false
    private var viewpage2Scoll = false
    private val scrollThreshold = 160
    private var isScratched = false

    // 重置刮刮乐的状态
    fun resetScratchCard() {
        isScratched = true
        invalidate()
    }

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
        val random = Random()
        val randomNumber = random.nextInt(2)
        val resourceId = if (randomNumber == 0) {
            R.drawable.scratch1
        } else {
            R.drawable.scratch2
        }
        mBitmapBackground = BitmapFactory.decodeResource(resources, resourceId)
        mCanvas = Canvas()
        mPaintText = Paint()
        mPaintText!!.color = Color.WHITE
        mPaintText!!.textSize = 100f
        mPaintText!!.strokeWidth = 20f

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(mBitmapBackground, 0f, 0f, null)
        canvas.drawBitmap(mBitmapFront, 62f, 36f, paint)
        if (showFullResult) {
            // 绘制完整的刮奖结果
            paint?.alpha = 0 // 设置透明
            canvas.drawBitmap(mBitmapFront, 62f, 36f, paint)
        } else {
            // 绘制刮动的路径线条
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmapBackground = getBitmap(mBitmapBackground, w, h)
        mBitmapFront = Bitmap.createBitmap(
            mBitmapBackground.width - 124,

            mBitmapBackground.height - 72 , Bitmap.Config.ARGB_8888
        )
        area2 = (mBitmapFront.width * mBitmapFront.height).toFloat()
        LogUtils.d(
            "area2=" + area2+"; mBitmapFront.width=" + mBitmapFront.width + "; mBitmapFront.width=" + mBitmapFront.height
        )
        mCanvas!!.setBitmap(mBitmapFront)
        drawText(mCanvas, w, h)
    }


    var quarterWidth = GetScreenUtils.getScreenWidth(context)/4
    var halfWidth = GetScreenUtils.getScreenWidth(context)/2

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path!!.reset()
                path!!.moveTo(event.x -62, event.y -36) //原点移动至手指的触摸点
                startX = event.x -62
                startY = event.y -36
            }

            MotionEvent.ACTION_MOVE -> {
                path!!.lineTo(event.x - 62, event.y - 36)
                endX = event.x -62
                endY = event.y -36

            }
            MotionEvent.ACTION_UP -> {
                // 处理 ACTION_UP 事件
                // 进行其他操作或处理滑动面积
                disX = Math.abs(endX - startX)
                disY = Math.abs(endY - startY)
                val diagonal = Math.sqrt((disX * disX + disY * disY).toDouble())
                area = diagonal.toFloat() * 50f // 计算滑动的面积
                sum += area
                LogUtils.d(
                    "area=" + area+"; disX=" + disX + "; disY=" + disY + "; sum=" + sum
                )
            }

        }


        //上下冲突
        if ((endY - startY) < scrollThreshold) {
            shouldInterceptScroll = true
        } else {
            shouldInterceptScroll = false
        }

        //1/4时显示全部
        if (sum > (area2/4)) {
            showFullResult = true
        } else {
            showFullResult = false
        }

        if ((endX - startX) > halfWidth) {
            viewpage2Scoll = true
        } else {
            viewpage2Scoll = false
        }

        mCanvas!!.drawPath(path!!, pathPaint!!)
        invalidate()

        if (shouldInterceptScroll) {
            parent.requestDisallowInterceptTouchEvent(true)
        }

        /*if (disX > disY) {
            //如果是纵向滑动，告知父布局不进行时间拦截，交由子布局消费，　requestDisallowInterceptTouchEvent(true)
            parent.requestDisallowInterceptTouchEvent(canScrollHorizontally(startX - endX))
        } else {
            parent.requestDisallowInterceptTouchEvent(canScrollVertically(startX - endX))
        }*/

        /*if (viewpage2Scoll) {
            parent.requestDisallowInterceptTouchEvent(false)
        }else{
            parent.requestDisallowInterceptTouchEvent(true)
        }*/
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

    fun getBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        // 获得图片的宽高
        val width = bm.width
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

    /*fun setCustomViewB(customViewB: ScratchCardView) {
        this.customViewB = customViewB
    }

    fun getValueFromCustomViewB(): Boolean {
        return customViewB?.getValue() ?: false
    }

    fun someFunction() {
        // 在 CustomViewA 内部调用 setCustomViewB 和 getValueFromCustomViewB 方法
        val scratchCardView = ScratchCardView(context)
        this.setCustomViewB(scratchCardView)

        isOnceMore = this.getValueFromCustomViewB()

        // 使用获取到的 value 进行后续操作
    }

    //这个方法可以在外部调用，用于将自定义 View B 的实例传递给自定义 View A。*/
}