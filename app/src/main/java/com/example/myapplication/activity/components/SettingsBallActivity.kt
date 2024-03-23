package com.example.myapplication.activity.components


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySettingsBallBinding

// SettingsBallActivity 是设置页面的活动类，负责修改小球的设置。
class SettingsBallActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("BallViewSettings", Context.MODE_PRIVATE)

        // 初始化设置页面的控件状态
        binding.run {
            // 设置小球大小的滑动条
            seekBarSize.progress = sharedPref.getInt("ballSize", 50)
            // 设置重力感应模式的开关
            switchMode.isChecked = sharedPref.getBoolean("gravityMode", false)
            // 根据保存的颜色设置选中的颜色按钮
            when (sharedPref.getInt("ballColor", Color.BLUE)) {
                Color.WHITE -> radioGroupColor.check(R.id.rbWhite)
                Color.RED -> radioGroupColor.check(R.id.rbRed)
                Color.BLUE -> radioGroupColor.check(R.id.rbBlue)
                Color.YELLOW -> radioGroupColor.check(R.id.rbYellow)
            }

            // 监听小球大小滑动条的变化
            seekBarSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {
                    with(sharedPref.edit()) {
                        putInt("ballSize", progress)
                        apply()
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // 不需要实现此方法，但必须重写
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // 不需要实现此方法，但必须重写
                }
            })

            // 监听颜色选择的变化
            radioGroupColor.setOnCheckedChangeListener { _, checkedId ->
                val color = when (checkedId) {
                    R.id.rbWhite -> Color.WHITE
                    R.id.rbRed -> Color.RED
                    R.id.rbBlue -> Color.BLUE
                    R.id.rbYellow -> Color.YELLOW
                    else -> Color.BLUE // 默认颜色
                }
                with(sharedPref.edit()) {
                    putInt("ballColor", color)
                    apply()
                }
            }

            // 监听重力感应模式开关的变化
            switchMode.setOnCheckedChangeListener { _, isChecked ->
                with(sharedPref.edit()) {
                    putBoolean("gravityMode", isChecked)
                    apply()
                }
            }

            /*with 函数允许你对一个对象执行一个代码块，而不需要重复引用该对象。
            在这个代码块中，你可以直接调用对象的方法或访问其属性，而不需要使用对象的名称。*/

            /*sharedPref.edit() 创建了一个 SharedPreferences.Editor 对象，用于修改 SharedPreferences 中的值。
            SharedPreferences 是 Android 中一种轻量级的存储解决方案，常用于保存应用的配置数据。

with 函数接受这个 SharedPreferences.Editor 对象作为参数，并执行一个代码块。在这个代码块内，
this 关键字指向 SharedPreferences.Editor 对象。

在 with 代码块内部，调用了 putInt("ballColor", color) 方法。
这个方法将一个整型值（color）与一个键（"ballColor"）关联起来，并保存到 SharedPreferences 中。
这里，color 是一个表示颜色的整型值。

apply() 方法是 SharedPreferences.Editor 的一个方法，用于异步提交修改到 SharedPreferences。
apply() 方法不会阻塞主线程，它会在后台线程中把修改应用到 SharedPreferences 文件中。
总的来说，这段代码的作用是将一个表示颜色的整型值保存到 SharedPreferences 中，键名为 "ballColor"。
使用 with 函数可以让这个过程更加简洁，避免了重复引用 SharedPreferences.Editor 对象。*/
        }
    }
}