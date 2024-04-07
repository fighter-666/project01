package com.example.myapplication.call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}