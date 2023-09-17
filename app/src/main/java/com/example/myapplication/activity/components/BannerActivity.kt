package com.example.myapplication.activity.components

import android.R.attr.banner
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.adapter.ImageAdapter
import com.example.myapplication.data.DataBean
import com.example.myapplication.databinding.ActivityBannerBinding
import com.google.android.material.snackbar.Snackbar
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener


class BannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageAdapter = ImageAdapter(DataBean.testData2)
        //加载本地图片
        //加载本地图片
        binding.banner.setAdapter(imageAdapter)
            .addBannerLifecycleObserver(this)
            .setIndicator(CircleIndicator(this))
            .setOnBannerListener(OnBannerListener<Any?> { data, position ->
                Log.i(TAG, "position: $position")
            })

     /*   binding.banner.setAdapter(object : BannerImageAdapter<DataBean>(DataBean.testData3) {
            override fun onBindView(holder: BannerImageHolder, data: DataBean, position: Int, size: Int) {
                Glide.with(holder.imageView)
                    .load(data.imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        })*/


    }
}