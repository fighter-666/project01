package com.example.myapplication.call

data class RecentCall(
    val id: String,
    val last_call_time: Long
)

data class UserIdRequest(
    val id: String,
    val updated_at: Long
)

data class UserInfo(
    val id: String,
    val name: String,
    val gender: Int,
    val phone_number: String,
    val updated_at: Long
)