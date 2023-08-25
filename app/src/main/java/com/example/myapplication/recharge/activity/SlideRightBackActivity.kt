package com.example.myapplication.recharge.activity


import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.VelocityTracker


/**
 *
 * @company KCS互联网有限公司
 *
 * @author kincai
 *
 * @description baseActivity
 *
 * @project Kincai_Store
 *
 * @package com.kincai.store.ui
 *
 * @time 2015-7-13 上午10:18:29
 */
open class SlideRightBackActivity : Activity() {
    // 记录手指按下时的横坐标。
    private var xDown = 0f

    // 记录手指按下时的纵坐标。
    private var yDown = 0f

    // 记录手指移动时的横坐标。
    private var xMove = 0f

    // 记录手指移动时的纵坐标。
    private var yMove = 0f

    // 用于计算手指滑动的速度。
    private var mVelocityTracker: VelocityTracker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        createVelocityTracker(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                xDown = event.rawX
                yDown = event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                xMove = event.rawX
                yMove = event.rawY
                // 滑动的距离
                val distanceX = (xMove - xDown).toInt()
                val distanceY = (yMove - yDown).toInt()
                // 获取顺时速度
                val ySpeed = scrollVelocity
                // 关闭Activity需满足以下条件：
                // 1.x轴滑动的距离>XDISTANCE_MIN
                // 2.y轴滑动的距离在YDISTANCE_MIN范围内
                // 3.y轴上（即上下滑动的速度）<XSPEED_MIN，如果大于，则认为用户意图是在上下滑动而非左滑结束Activity
                if (distanceX > XDISTANCE_MIN && distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN && ySpeed < YSPEED_MIN) {
                    finish()
                }
            }

            MotionEvent.ACTION_UP -> recycleVelocityTracker()
            else -> {}
        }
        return super.dispatchTouchEvent(event)
    }

    /**
     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中。
     *
     * @param event
     */
    private fun createVelocityTracker(event: MotionEvent) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker!!.addMovement(event)
    }

    /**
     * 回收VelocityTracker对象。
     */
    private fun recycleVelocityTracker() {
        mVelocityTracker!!.recycle()
        mVelocityTracker = null
    }//1000速度

    /**
     *
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    private val scrollVelocity: Int
        private get() {
            //1000速度
            mVelocityTracker!!.computeCurrentVelocity(1000)
            val velocity = mVelocityTracker!!.yVelocity.toInt()
            return Math.abs(velocity)
        }

    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart()
    }

    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
    }

    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
    }

    override fun onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy()
    }

    companion object {
        // 手指上下滑动时的最小速度
        private const val YSPEED_MIN = 1000

        // 手指向右滑动时的最小距离
        private const val XDISTANCE_MIN = 50

        // 手指向上滑或下滑时的最小距离
        private const val YDISTANCE_MIN = 66
        private const val TAG = "BaseActivity"
    }
}