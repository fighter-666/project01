package com.example.myapplication.extend

operator fun String.times(n:Int): String {
    val builder =StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}