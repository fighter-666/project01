package com.example.myapplication

import com.example.myapplication.data.Cellphone

fun main() {
    val cellphone1 = Cellphone("Samsung", 200.2)
    val cellphone2 = Cellphone("Samsung", 200.2)
    println(cellphone1)
    println("cellphone2 eqials cellphone1  " + (cellphone2 == cellphone1))

    listOf("aaa", "bbb", "ccc").forEach { println(it)}
    val list = listOf("aaa", "bbb", "ccc")
    for (item in list){
        println(item)
    }

    Thread{
        println("Hello1111111")
    }.start()

    Thread{
        println("Hello11111112222")
    }

    val brand ="aaa"
    val price = 100.0
    println("Cellphone brand: $brand, price: $price")
}