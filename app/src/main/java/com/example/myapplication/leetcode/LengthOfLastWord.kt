package com.example.myapplication.leetcode

/**
 * 58.最后一个单词的长度
 *
 * 给你一个字符串s ，有若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度
 *
 * 单词 是指仅有字母组成、不包含任何空格字符的最大子字符串
 *
 */
class LengthOfLastWord {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(LengthOfLastWord().lengthOfLastWord("Hello World"))
            println(LengthOfLastWord().lengthOfLastWord("HelloWorld"))
            println(LengthOfLastWord().lengthOfLastWord("   fly me   to   the moon  "))
            println(LengthOfLastWord().lengthOfLastWord("luffy is still joyboy"))
        }
    }

    fun lengthOfLastWord(s: String): Int {
        var end = s.length - 1
        while (end >= 0 && s[end] == ' ') end--
        if (end < 0) return 0
        var start = end
        while (start >= 0 && s[start] != ' ') start--
        return end - start
    }
}