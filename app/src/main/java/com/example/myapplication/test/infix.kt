package com.example.myapplication.test

infix fun String.beginsWith(prefix: String) = startsWith(prefix)
fun main() {
    if ("Kotlin" beginsWith "Kot"){
        println("The string starts with 'Kot'.")
    }

    val result1 = getGenericType<String>()
    val result2 = getGenericType<Int>()
    println("result1 is $result1")
    println("result2 is $result2")
}

inline fun <reified T> getGenericType() = T::class.java