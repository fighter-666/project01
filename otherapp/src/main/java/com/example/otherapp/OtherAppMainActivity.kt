package com.example.otherapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.otherapp.databinding.ActivityOtherAppMainBinding

class OtherAppMainActivity : ComponentActivity() {
    private lateinit var binding: ActivityOtherAppMainBinding
    private lateinit var mServiceIntent: Intent
    private val TAG = "OtherAppMainActivity"
    private var mServiceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherAppMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mServiceIntent = Intent()
        mServiceIntent.setComponent(ComponentName("com.example.myapplication", "com.example.myapplication.MainAppService"))


        binding.run {
            tvBind.setOnClickListener {
                Log.d(TAG, "bind")
                bindService(mServiceIntent, mServiceConnection!!, Context.BIND_AUTO_CREATE)
            }

            tvUnbind.setOnClickListener {
                Log.d(TAG, "unBind")
                if (mServiceConnection != null) {
                    unbindService(mServiceConnection!!)
                }
            }
        }
    }
}