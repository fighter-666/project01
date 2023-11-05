package com.example.myapplication.util

import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlin.experimental.ExperimentalTypeInference

class ItemBind {
    //@BuilderInference(value = ["android:imgUrl"])
    fun setUserPhoto(iView: ImageView,imageUrl:String){
        Picasso.get().load(imageUrl).into(iView)
    }
}