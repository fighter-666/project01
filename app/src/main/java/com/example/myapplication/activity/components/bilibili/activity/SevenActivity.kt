package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.SeekBar
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityServeBinding

class ServeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServeBinding
    private  var Chinese: String = ""
    private  var English: String = ""
    private  var Math: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonleft.setOnClickListener {
            binding.display.setText(R.string.button1)
        }
        binding.buttonright.setOnClickListener {
            binding.display.setText(R.string.button2)
        }
        binding.switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.display.text = "开"
            } else {
                binding.display.text = "关"
            }
        }
        binding.button3.setOnClickListener {
            var s = binding.editTextNumber.text.toString()
            if (TextUtils.isEmpty(s)) {
                s = "0"
            }
            binding.progressBar2.setProgress(s.toInt())
            binding.display.text = s
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButton){
                binding.imageView3.setImageResource(R.drawable.card1)
            } else {
                binding.imageView3.setImageResource(R.drawable.card2)
            }
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 在这里处理进度条值的变化
                binding.display.text = progress.toString()
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
            binding.display.text = Chinese+English+Math
        }
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                English = "英语"
            } else {
                English = ""
            }
            binding.display.text = Chinese+English+Math
        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Math = "数学"
            } else {
                Math = ""
            }
            binding.display.text = Chinese+English+Math
        }
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            // 在这里处理评分条值的变化
            Toast.makeText(applicationContext,rating.toString() + "星评价！", Toast.LENGTH_SHORT).show()
        }


    }
}