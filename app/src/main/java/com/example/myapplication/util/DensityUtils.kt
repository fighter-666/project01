package com.example.myapplication.util

import android.content.Context


//dp是待转换的dp值，它表示设备无关像素。
//scale是设备的像素密度比例，通过context.resources.displayMetrics.density获取。
// 它代表了设备实际像素和设备独立像素之间的比例关系。例如，对于度为160dpi的设备，
// 密度比例为1；对于320dpi的设备，密度比例为2。 0.5f是为了实现四舍五入效果，
// 当转换结果为小数时，加上0.5可以让小数部分达到或超过0.5时，整数部分进位为1，否则舍去。
//这个公式的基本思想是将dp值乘以密度比例，得到实际像素值（可能是一个小数），
// 然后通过加上0.5并将结果转换为整数，实现四舍五入的效果。
object DensityUtils {
    fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}