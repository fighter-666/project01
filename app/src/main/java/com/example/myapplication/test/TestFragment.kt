package com.example.myapplication.test

import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.FragmentTest2Binding

class HomeFragment: BaseBindingFragment<FragmentTest2Binding>(FragmentTest2Binding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
