package com.example.myapplication.call

// MainActivity.kt
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.CallAdapter
import com.example.myapplication.databinding.ActivityCallBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchRecentCalls()
    }

    private fun fetchRecentCalls() {
        NetworkClient.apiService.getRecentCalls().enqueue(object : Callback<List<RecentCall>> {
            override fun onResponse(call: Call<List<RecentCall>>, response: Response<List<RecentCall>>) {
                if (response.isSuccessful) {
                    val recentCalls = response.body()!!
                    val sortedCalls = recentCalls.sortedByDescending { it.last_call_time }
                    updateUI(sortedCalls)
                }
            }

            override fun onFailure(call: Call<List<RecentCall>>, t: Throwable) {
                Log.e("CallActivity", "Failed to fetch recent calls: ${t.message}")
            }
        })
    }

    private fun updateUI(recentCalls: List<RecentCall>) {
        // 实现UI更新逻辑，例如更新RecyclerView的数据
        binding.run {
            val callAdapter =
                CallAdapter(
                    R.layout.adapter_recommendation_service,
                    recentCalls
                )
            rlCall.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = callAdapter
            }
        }

    }
}