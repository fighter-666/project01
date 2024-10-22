package com.example.myapplication.leetcode

import java.util.LinkedList
import kotlin.math.min

/**
 * 155.最小栈
 *
 * 设计一个支持push, pop, top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现MinStack 类
 * MInStack() 初始换堆栈对象。
 * void push(int val) 将元素val 推入堆栈
 * void pop() 删除堆栈顶部的元素
 * int top() 获取堆栈顶部的元素
 * int getMin() 多去堆栈中的最小元素
 */
class MinStack {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            MinStack().apply {
                push(-2)
                push(0)
                push(-3)
                println(getMin())
                pop()
                println(top())
                println(getMin())

            }
        }
    }

    private val stack = LinkedList<Int>()
    private val minStack = LinkedList<Int>().apply {
        push(Int.MAX_VALUE)
    }

    fun push(number: Int){
        stack.push(number)
        minStack.push(min(minStack.peek(), number))
    }

    fun pop(){
        stack.pop()
        minStack.pop()
    }

    fun top():Int{
        return stack.peek()
    }

    fun getMin(): Int{
        return minStack.peek()
    }
}