package com.example.myapplication.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.example.myapplication.activity.components.SisthActivity

class MyClickableSpan(private val content: String, private val context: Context) : ClickableSpan() {
    override fun updateDrawState(ds: TextPaint) {
        ds.setUnderlineText(false)
    }

    override fun onClick(widget: View) {
        val intent = Intent(context, SisthActivity::class.java)
        val bundle = Bundle()
        bundle.putString("content", content)
        intent.putExtra("bundle", bundle)
        context.startActivity(intent)
    }
}