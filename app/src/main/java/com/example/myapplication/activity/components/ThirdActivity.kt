package com.example.myapplication.activity.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.ComplexItemEntity
import com.example.myapplication.databinding.ActivityThirdBinding


class ThirdActivity : AppCompatActivity(){
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val complexDatas: MutableList<ComplexItemEntity> = ArrayList()
        for (i in 0..4) {
            complexDatas.add(ComplexItemEntity("标题 $i", "副标题 $i", "时间 $i", "内容 $i"))
        }
    }




}

