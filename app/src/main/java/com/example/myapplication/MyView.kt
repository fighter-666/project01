

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.view.View


class MyView(context: Context,): View(context) {

    @SuppressLint("ResourceType")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val path = Path()
        val matrix = Matrix()
        val camera = Camera()
        paint.style = Paint.Style.FILL
        paint.setColor(Color.RED)


        /*val drawable = resources.getDrawable(R.drawable.batman)
        val batman: View = drawable
        batman.animate().translationX(500f)*/

        /*val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
        canvas.drawBitmap(bitmap,100f,100f,paint)
*/

        //canvas.save()
        //canvas.clipRect(200f,200f,400f,400f)

        //canvas.rotate(45f,200f,0f)
        //canvas.translate(200f,0f)
       /* val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        canvas.scale(1.5f, 1.5f, x + bitmapWidth / 2, y + bitmapHeight / 2);*/

        //canvas.skew(0f,0.5f)
        /*val point1 = Point(50, 200)
        val point2 = Point(550, 200)*/
        /*val left = (width - bitmap.width) / 2
        val top = (height - bitmap.height) / 2

        canvas.drawBitmap(bitmap,left.toFloat(),top.toFloat(),paint)*/
        /*canvas.drawBitmap(bitmap,point1.x.toFloat(),point1.y.toFloat(),paint)
        canvas.drawBitmap(bitmap,point2.x.toFloat(),point2.y.toFloat(),paint)
        canvas.restore()*/

        /*canvas.save()
        camera.save()
        camera.rotateX(30f)
        canvas.translate(200f,500f)
        camera.applyToCanvas(canvas)
        canvas.translate(-200f,-500f)
        camera.restore()
        canvas.drawBitmap(bitmap,100f,400f,paint)
        canvas.restore()*/
    }
}