package com.example.myapplication.activity.components


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBallViewBinding
import com.example.myapplication.databinding.ActivitySettingsBallBinding

class SettingsBallActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            val sharedPref = getSharedPreferences("BallViewSettings", Context.MODE_PRIVATE)

            // 设置初始值
            seekBarSize.progress = sharedPref.getInt("ballSize", 50)
            switchMode.isChecked = sharedPref.getBoolean("gravityMode", false)
            when (sharedPref.getInt("ballColor", Color.BLUE)) {
                Color.BLUE -> radioGroupColor.check(R.id.radioButtonBlue)
                Color.RED -> radioGroupColor.check(R.id.radioButtonRed)
                Color.GREEN -> radioGroupColor.check(R.id.radioButtonGreen)
            }

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

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            radioGroupColor.setOnCheckedChangeListener { group, checkedId ->
                val color = when (checkedId) {
                    R.id.radioButtonBlue -> Color.BLUE
                    R.id.radioButtonRed -> Color.RED
                    R.id.radioButtonGreen -> Color.GREEN
                    else -> Color.BLUE
                }
                with(sharedPref.edit()) {
                    putInt("ballColor", color)
                    apply()
                }
            }

            switchMode.setOnCheckedChangeListener { _, isChecked ->
                with(sharedPref.edit()) {
                    putBoolean("gravityMode", isChecked)
                    apply()
                }
            }
        }
    }
}