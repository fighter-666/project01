package com.example.myapplication.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityReturnPageBinding
import com.gyf.immersionbar.ImmersionBar


class ReturnActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReturnPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReturnPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init();

        //返回值并退出
        binding.tvAbandon.setOnClickListener {
            val intent1 = Intent().apply {
                putExtra("result", 1)
            }
            setResult(Activity.RESULT_OK, intent1)
            finish()
        }
    }
}