package com.example.myapplication.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityBaseApplicationBinding

class BaseApplication : AppCompatActivity() {
    val context: Context = this
    private lateinit var binding: ActivityBaseApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
          /*  val accountBean = AccountBean(
                accountId = 1,
                loginAccount = getLoginAccount(),
                loginPassword = getLoginPassword()
            )
            AccountDataBase.accountDb.accountDao.insert(accountBean)*/
        }
    }

    private fun getLoginPassword(): String {
        return binding.etLoginPassword.text.toString()
    }

    private fun getLoginAccount(): String {
        return binding.etLoginAccount.text.toString()
    }
}