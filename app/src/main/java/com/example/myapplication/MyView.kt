package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.View

class MyView(context: Context?): View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val path = Path()
        paint.style = Paint.Style.FILL
        paint.setColor(Color.RED)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
        canvas.save()
        //canvas.clipRect(200f,200f,400f,400f)

        canvas.rotate(45f,200f,0f)
        canvas.translate(200f,0f)
        canvas.drawBitmap(bitmap,100f,100f,paint)
        canvas.restore()
    }
}