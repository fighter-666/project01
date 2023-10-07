package com.example.myapplication.widget

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

open class MyFragmentObserver : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        // Fragment is visible to user
    }

    override fun onPause(owner: LifecycleOwner) {
        // Fragment is not visible to user
    }
}
