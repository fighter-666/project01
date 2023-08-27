package com.example.myapplication.activity.components.bilibili.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.util.ViewModelWithScore
import com.example.myapplication.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var viewModelWithScore: ViewModelWithScore
    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_score)
        viewModelWithScore = ViewModelProvider(this).get(ViewModelWithScore::class.java)
        binding.setData(viewModelWithScore)
        binding.setLifecycleOwner(this)
    }
}
