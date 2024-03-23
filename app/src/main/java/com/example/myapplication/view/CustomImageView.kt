package com.example.myapplication.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 自定义ImageView，用于显示等比缩放并裁剪的图片。
 *
 * @param context 上下文环境。
 * @param attrs 属性集。
 * @param defStyleAttr 默认样式属性。
 */
class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    // 用于绘制图片的画笔，启用抗锯齿。
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 原始图片。
    private var sourceBitmap: Bitmap? = null
    // 缩放并裁剪后的图片。
    private var scaledBitmap: Bitmap? = null

    init {
        // 初始化代码（如果需要）
    }

    /**
     * 重写setImageBitmap方法，设置图片时进行缩放和裁剪处理。
     *
     * @param bitmap 要设置的Bitmap对象。
     */
    override fun setImageBitmap(bitmap: Bitmap?) {
        super.setImageBitmap(bitmap)
        sourceBitmap = bitmap
        prepareAndScaleBitmap()
    }

    /**
     * 准备并缩放Bitmap，根据目标尺寸等比缩放并裁剪图片。
     */
    private fun prepareAndScaleBitmap() {
        sourceBitmap?.let { bitmap ->
            // 目标尺寸
            val targetWidth = 140f
            val targetHeight = 80f

            // 计算缩放比例
            /*这行代码计算缩放比例 scale。它首先确定目标宽度 targetWidth（在这个例子中是140px），
            然后除以原始 Bitmap 的宽度和高度中较大的一个。coerceAtLeast 函数确保了除数
            （即 bitmap.width 和 bitmap.height 中的较大值）不会小于对方，这样做是为了保持图片的宽高比，
            避免图片被拉伸变形。计算出的 scale 值表示原始图片需要被缩放的比例，以便其宽度或高度匹配目标尺寸，
            同时保持图片的宽高比不变。*/
            val scale = targetWidth / bitmap.width.coerceAtLeast(bitmap.height)

            /*这行代码创建了一个 Matrix 对象，并通过 apply 函数调用 postScale 方法来设置缩放比例。
            postScale 方法接受两个参数，分别是宽度和高度的缩放比例，在这里两者都使用了之前计算出的 scale 值，
            意味着图片将等比缩放。Matrix 对象用于在绘制 Bitmap 时应用变换，如缩放、旋转等*/
            val matrix = Matrix().apply { postScale(scale, scale) }

            // 计算裁剪区域
            val scaledWidth = bitmap.width * scale
            val scaledHeight = bitmap.height * scale

            /*计算yOffset，这是垂直方向上需要裁剪掉的部分的一半。因为目标是保持图片中心不变，
            所以如果缩放后的图片比目标高度要高，就需要在上下各裁剪掉一部分。计算方式是将缩放后的高度减去目标高度，
            然后除以2，得到的结果是从缩放后的图片顶部开始到裁剪区域顶部的距离*/
            val yOffset = (scaledHeight - targetHeight) / 2

            // 创建缩放后的Bitmap
            /*使用Bitmap.createBitmap方法创建一个新的Bitmap对象，这个对象是原始图片的一个裁剪和缩放版本。
            这个方法的参数包括：bitmap：原始的Bitmap对象。
0：裁剪区域的起始X坐标，这里是0，表示从左侧开始裁剪。

(yOffset / scale).toInt()：裁剪区域的起始Y坐标，这里需要将yOffset除以scale来转换回原始图片的尺寸，
然后转换为整数。

bitmap.width：裁剪区域的宽度，这里使用原始图片的宽度，因为不需要在水平方向上裁剪。

(targetHeight / scale).toInt()：裁剪区域的高度，这里将目标高度除以scale来转换回原始图片的尺寸，
然后转换为整数。

matrix：应用于图片的Matrix对象，包含了之前计算的缩放比例。
true：表示如果需要，可以过滤（抗锯齿等）。*/
            val scaledBitmap = Bitmap.createBitmap(
                bitmap, 0, (yOffset / scale).toInt(),
                bitmap.width, (targetHeight / scale).toInt(), matrix, true
            )

            this.scaledBitmap = scaledBitmap
        }
    }

    /**
     * 重写onDraw方法，绘制缩放并裁剪后的图片。
     *
     * @param canvas 画布对象。
     */
    override fun onDraw(canvas: Canvas?) {
        scaledBitmap?.let {
            // 绘制缩放后的Bitmap
            val destRect = RectF(0f, 0f, width.toFloat(), height.toFloat())
            canvas?.drawBitmap(it, null, destRect, paint)
        } ?: super.onDraw(canvas)
    }
}