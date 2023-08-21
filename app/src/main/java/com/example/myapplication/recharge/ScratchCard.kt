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
    private var shouldInterceptScroll = false
    private var showFullResult = false
    private var isScratched = false
    var scaleWidth :Float = 0f
    var scaleHeight :Float = 0f

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
        canvas.drawBitmap(mBitmapFront,  (mBitmapBackground.width*0.06).toFloat(), (mBitmapBackground.height*0.1).toFloat(), paint)
        if (showFullResult) {
            // 绘制完整的刮奖结果
            paint?.alpha = 0 // 设置透明
            canvas.drawBitmap(mBitmapFront, (mBitmapBackground.width*0.06).toFloat(), (mBitmapBackground.height*0.1).toFloat(), paint)
        } else {
            // 绘制刮动的路径线条
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmapBackground = getBitmap(mBitmapBackground, w, h)
        mBitmapFront = Bitmap.createBitmap(
            (mBitmapBackground.width*0.88).toInt(),

            (mBitmapBackground.height*0.8).toInt() , Bitmap.Config.ARGB_8888
        )
        area2 = (mBitmapFront.width * mBitmapFront.height).toFloat()
        LogUtils.d(
            "area2=" + area2+"; mBitmapFront.width=" + mBitmapFront.width + "; mBitmapFront.width=" + mBitmapFront.height
        )
        mCanvas?.setBitmap(mBitmapFront)
        drawText(mCanvas, w, h)
    }


    var quarterWidth = GetScreenUtils.getScreenWidth(context)/4
    var halfWidth = GetScreenUtils.getScreenWidth(context)/2

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path!!.reset()
                path!!.moveTo(event.x -(mBitmapBackground.width*0.06).toFloat(), event.y -(mBitmapBackground.height*0.1).toFloat()) //原点移动至手指的触摸点
                startX = event.x
                startY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                path!!.lineTo(event.x - (mBitmapBackground.width*0.06).toFloat(), event.y - (mBitmapBackground.height*0.1).toFloat())
                endX = event.x
                endY = event.y

            }
            MotionEvent.ACTION_UP -> {
                // 处理 ACTION_UP 事件
                // 进行其他操作或处理滑动面积
                disX = Math.abs(endX - startX)
                disY = Math.abs(endY - startY)
                val diagonal = Math.sqrt((disX * disX + disY * disY).toDouble())
                area = diagonal.toFloat() * 50f // 计算滑动的面积
                sum += area

            }

        }


        val bitmap: Bitmap = mBitmapFront // 从某处获取位图对象
        val w: Int = bitmap.width // 位图的宽度
        val h: Int = bitmap.height // 位图的高度
        val mPixels: IntArray = IntArray(w * h) // 创建一个整型数组来存储像素数据

        bitmap.getPixels(mPixels, 0, w, 0, 0, w, h)
        var wipeArea = 0f
        val totalArea = w * h.toFloat()

        for (i in 0 until w) {
            for (j in 0 until h) {
                val index = i + j * w
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
            if(percent>25){
                showFullResult = true
            }
            LogUtils.d(
                "totalArea=" + totalArea+"; wipeArea=" + wipeArea + "; percent=" + percent + "; showFullResult=" + showFullResult
            )
        }


// 现在 mPixels 数组中存储了位图的像素数据


        //上下冲突
        if ((endY - startY) < mBitmapBackground.height) {
            shouldInterceptScroll = true
        } else {
            shouldInterceptScroll = false
        }


        if ((endX - startX) > mBitmapBackground.width) {
            parent.requestDisallowInterceptTouchEvent(true)
        } else {
            parent.requestDisallowInterceptTouchEvent(false)
        }

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

    fun getBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        // 获得图片的宽高
        val width = bm.width
        val height = bm.height
        // 计算缩放比例
        scaleWidth = newWidth.toFloat() / width
        scaleHeight = newHeight.toFloat() / height
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        LogUtils.d(
            "111width=" + width + "; height=" + height+ "; newWidth=" + newWidth+ "; scaleHeight=" + scaleHeight+ "; scaleWidth=" + scaleWidth
        )
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true)
    }


}