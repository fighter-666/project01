package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFirstRoomBinding
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.User
import kotlin.concurrent.thread

class FirstRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.d("FirstRoomActivity",user.toString())
                }
            }
        }
    }
}