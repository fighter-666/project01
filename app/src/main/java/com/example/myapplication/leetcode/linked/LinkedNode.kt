package com.example.myapplication.leetcode.linked
/**
 * 定义一个简单的单向链表节点类，并提供一个printAll来打印链表中所有节点的值
 *
 * @param  value 是节点存储的值
 * @param  next 是指向下一个节点的引用，默认为null，表示链表的末尾
 * @return
 */
class LinkedNode<T>(var value: T, var next: LinkedNode<T>? = null) {
    fun printAll() {
        println("$value")
        var temp = next
        while (temp != null) {
            println(temp.value)
            temp = temp.next
        }
    }
}