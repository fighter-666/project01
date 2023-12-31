package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.data.PersonTest
import com.example.myapplication.data.User
import com.example.myapplication.databinding.ActivityUserBinding
import com.example.myapplication.util.ClickHandlers

class UserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConfirm.setOnClickListener {
            val user = getUser()
            binding.user = user
        }
        binding.clickHandlers = ClickHandlers()

       /* binding.btnConfirm.setOnClickListener {
            val user = getUser()
            binding.tvName.text = user.userName
            binding.tvId.text = user.userId
        }*/

        val person = intent.getSerializableExtra("person_data") as PersonTest
        Log.d("person",person.name)
        Log.d("person",person.age.toString())
    }

    private fun getUser(): User {
        return User(getUserName(),getUserId())
    }

    private fun getUserId(): String? {
        return binding.etId.text?.toString()

    }

    private fun getUserName(): String? {
        return binding.etName.text?.toString()
    }
}