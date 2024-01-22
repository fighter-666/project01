package com.example.myapplication.test

fun main() {
    val num1 = 100
    val num2 = 80
    /*//::plus 是一种函数引用
    val result1 = num1AndNum2(num1, num2, ::plus)
    val result2 = num1AndNum2(num1, num2, ::minus)*/

    //使用lambda表达式
    //lambda表达式中的最后一行代码会自动作为返回值
    val result1 = num1AndNum2(num1, num2) { a, b -> a + b}
    val result2 = num1AndNum2(num1, num2) { a, b -> a - b}
    println("result1 is $result1")
    println("result2 is $result2")

    val list = listOf("apple", "banana", "orange")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Yummy, I'm full now!")
    }
    println(result.toString())

    val myClass = MyClass<Int>()
    val result3 = myClass.method(3)
    println(result3)

    val myClass2 = MyClass2()
    val result4 = myClass2.method(3)
    println(result4)
}

class MyClass2{
    fun <T: Number> method(param: T): T{
        return param
    }
}

//MyClass就是一个泛型类，MyClass中的方法允许使用T类型的参数和返回值
class MyClass<T> {
    fun method(param: T): T {
        return param
    }
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): java.lang.StringBuilder {
    block()
    return this
}

//第三个参数是一个接收两个整型参数并且返回值也是整型的函数类型参数。在num1AndNum2函数中，
//我们没有进行任何具体的操作，而是将num1和num2传递给了第三个函数类型参数，并获取他的返回值，最终得到返回值返回
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

//函数的参数声明和返回值声明都和num1AndNum2函数中的函数类型参数是完全匹配的
fun plus(num1: Int, num2: Int): Int{
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int{
    return num1 - num2
}