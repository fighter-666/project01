package com.example.myapplication.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//suspend关键字只能将一个函数声明成挂起函数，是无法给它提供协程作用域的
/*suspend fun printDot() = coroutineScope {
    launch {
        println(".")
        delay(1000)
    }
}*/

fun main() {
    runBlocking {
        //调用withContext()函数之后，会立即执行代码块中的代码，同时将当前协程阻塞住。
        // u当前代码快中的代码全部执行完之后，会将最后一行的执行结果作为withContext()函数的返回值返回
        val result = withContext(Dispatchers.Default){
            5+5
        }
        println(result)
    }

 /*   runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5+5
        }.await()
        val result2 = async {
            delay(1000)
            4+6
        }.await()
        println("result is ${result1+result2}")
        val end = System.currentTimeMillis()
        println("cost ${end-start}ms")
    }

    runBlocking {
        val start  = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5+5
        }
        val deferred2 = async {
            delay(1000)
            4+6
        }
        println("result is ${deferred1.await()+deferred2.await()}")
        val end = System.currentTimeMillis()
        println("cost ${end-start}milliseconds")
    }*/

   /* runBlocking {
        val result = async {
            5+5
        }.await()
        println(result)
    }*/

   /* runBlocking {
        coroutineScope {
            launch {
                for (i in 1..10){
                    println(i)
                    delay(1000)
                }
            }
        }
        println("coroutinrScope finished")
    }
    println("runBlocking finished")*/

  /*  val start = System.currentTimeMillis()
    runBlocking {
        repeat(100){
            launch{
                println(".")
            }
        }
        val end = System.currentTimeMillis()
        println(end - start)
    }*/

   /* GlobalScope.launch {
        println("code run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")
    }
    Thread.sleep(1000)// 等待协程执行*/

    //保证在协程的作用域内的所有代码和子协程没有全部执行完之前一直阻塞当前线程
   /* runBlocking {
        println("code run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")
    }*/

  /*  runBlocking {
        launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }
    }*/
}