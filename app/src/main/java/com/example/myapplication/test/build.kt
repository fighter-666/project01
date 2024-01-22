package com.example.myapplication.test

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}
fun main() {
    if ("Hello Kotlin".startsWith("Hello")){
        println("Starts with 'Hello'")
    }
}