package com.example.myapplication.widget

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

@Suppress("DEPRECATION")
open class MyBaseFragmentActivity : FragmentActivity() {
    private val TAG = "MyBaseFragmentActivity"
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragmentManager = supportFragmentManager
        for (index in fragmentManager.fragments.indices) {
            val fragment = fragmentManager.fragments[index] //找到第一层Fragment
            fragment?.let { handleResult(it, requestCode, resultCode, data) }
                ?: Log.w(
                    TAG, "Activity result no fragment exists for index: 0x"
                            + Integer.toHexString(requestCode)
                )
        }
    }

    /**
     * 递归调用，对所有的子Fragment生效
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private fun handleResult(fragment: Fragment, requestCode: Int, resultCode: Int, data: Intent?) {
        fragment.onActivityResult(requestCode, resultCode, data) //调用每个Fragment的onActivityResult
        Log.e(TAG, "MyBaseFragmentActivity")
        val childFragment = fragment.childFragmentManager.fragments //找到第二层Fragment
        for (f in childFragment) f?.let {
            handleResult(
                it,
                requestCode,
                resultCode,
                data
            )
        }
    }
}