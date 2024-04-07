package com.example.myapplication.call

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("contacts/conversation")
    fun getRecentCalls(): Call<List<RecentCall>>

    @POST("contacts/users")
    fun getUserInfo(@Body userIds: List<UserIdRequest>): Call<List<UserInfo>>
}