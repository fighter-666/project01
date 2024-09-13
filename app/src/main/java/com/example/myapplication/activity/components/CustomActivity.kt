package com.example.myapplication.activity.components

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCustomBinding
import com.example.myapplication.widget.CustomView
import com.gyf.immersionbar.ImmersionBar

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式
        ImmersionBar.with(this)
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init()

        //创建了一个自定义视图对象 customView 并将其设置为当前活动的内容视图。
        val customView = CustomView(this)
        setContentView(customView)

        val alertDialog7 = AlertDialog.Builder(this)
        val view1 = layoutInflater.inflate(R.layout.activity_alter_dialog_setview, null)
        val et: EditText = view1.findViewById(R.id.et)
        val bu: Button = view1.findViewById(R.id.btnDetail)

        alertDialog7.apply {
            setView(view1)
        }

        val show = alertDialog7.create()
        show.show()

// 设置弹窗位置
        val window = show.window
        window?.setGravity(Gravity.TOP or Gravity.LEFT) // 设置位置为左上角
        val layoutParams = window?.attributes
        layoutParams?.x = 10 // 设置x坐标
        layoutParams?.y = 200 // 设置y坐标
        layoutParams?.width = 900
        layoutParams?.height = 300

        window?.attributes = layoutParams

        bu.setOnClickListener {
            Toast.makeText(this, "电话" + et.text.toString(), Toast.LENGTH_SHORT).show()
            show.dismiss()
        }
    }
}