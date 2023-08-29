package com.example.myapplication.activity.components.bilibili.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCommonControlBinding

class CommonControlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommonControlBinding
    private var Chinese: String = ""
    private var English: String = ""
    private var Math: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonControlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btShowLeft.setOnClickListener {
            binding.tvDisplay.setText(R.string.button1)
        }
        binding.btShowRight.setOnClickListener {
            binding.tvDisplay.setText(R.string.button2)
        }
        binding.switchControl.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.tvDisplay.text = "开"
            } else {
                binding.tvDisplay.text = "关"
            }
        }
        binding.button3.setOnClickListener {
            var s = binding.editTextNumber.text.toString()
            if (TextUtils.isEmpty(s)) {
                s = "0"
            }
            binding.progressBar2.setProgress(s.toInt())
            binding.tvDisplay.text = s
        }
        binding.rgSwitchPicture.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbAndroid) {
                binding.imageView3.setImageResource(R.drawable.card1)
            } else {
                binding.imageView3.setImageResource(R.drawable.card2)
            }
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 在这里处理进度条值的变化
                binding.tvDisplay.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 用户开始触摸进度条时的处理逻辑
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 用户停止触摸进度条时的处理逻辑
            }
        })
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Chinese = "语文"
            } else {
                Chinese = ""
            }
            binding.tvDisplay.text = Chinese + English + Math
        }
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                English = "英语"
            } else {
                English = ""
            }
            binding.tvDisplay.text = Chinese + English + Math
        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Math = "数学"
            } else {
                Math = ""
            }
            binding.tvDisplay.text = Chinese + English + Math
        }
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            // 在这里处理评分条值的变化
            Toast.makeText(applicationContext, rating.toString() + "星评价！", Toast.LENGTH_SHORT)
                .show()
        }


    }
}