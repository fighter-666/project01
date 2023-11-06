package com.example.myapplication.activity.components.bilibili.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.activity.components.bilibili.data.MyDataBase
import com.example.myapplication.activity.components.bilibili.data.UserEntity
import com.example.myapplication.databinding.ActivityRoom2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Room2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRoom2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoom2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val userDao = MyDataBase.getDB(applicationContext).getUserDao()
        val TAG = "db"

        binding.btAdd.setOnClickListener {
            val account: String
            val nickname: String
            binding.apply {
                account = binding.tvAddAccount.text.toString()
                nickname = binding.tvAddNickName.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    userDao.insertUser(UserEntity(account = account, nickname = nickname))
                }
            }
        }
        binding.btGetAll.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userDao.getAll().forEach {
                    Log.e(TAG, it.toString())

                }

             /*   val userList = userDao.getAll()
                userList?.let {
                    binding.tvShow.text = ""
                    userList.forEach {
                        binding.tvShow.append("账号：${it.account} 昵称：${it.nickname}\n")
                    }
                }*/


            }
        }

        binding.btSearchAccount.setOnClickListener {
            val inputSearch = binding.tvAccountInput.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                userDao.getInfoByAct(inputSearch).forEach {
                    Log.e(TAG, it.toString())

                }
                userDao.getInfoByAct(inputSearch)?.let {
                    for (i in it.indices){
                  /*      binding.tvShow.append("账号：${it[i].account} ")
                        binding.tvShowNickName.append("昵称：${it[i].nickname} ")*/
                    }
                }
            }
        }
    }
}