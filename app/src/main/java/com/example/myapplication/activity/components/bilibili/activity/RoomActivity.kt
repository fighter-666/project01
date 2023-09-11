package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.data.Word
import com.example.myapplication.activity.components.bilibili.data.WordDao
import com.example.myapplication.activity.components.bilibili.data.WordDatabase
import com.example.myapplication.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var wordDao: WordDao
    private lateinit var wordDatabase: WordDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordDatabase = Room.databaseBuilder(this,WordDatabase::class.java,"word_datebase")
            .allowMainThreadQueries()
            .build()
        wordDao = wordDatabase.wordDao
        updateView()

        binding.btInsert.setOnClickListener {
            val word1 = Word("hello","您好！")
            val word2 = Word("world","世界！")
            wordDao.insertWords(word1,word2)
            updateView()
        }
        binding.btClear.setOnClickListener {
            wordDao.deleteAllWords()
            updateView()
        }
        binding.btUpdate.setOnClickListener {
            val word = Word("Hi","哇哈哈")
            word.id = 65
            wordDao.updateWords(word)
            updateView()
        }
        binding.btDelete.setOnClickListener {
            val word = Word("Hi","哇哈哈")
            word.id = 66
            wordDao.deleteWords(word)
            updateView()
        }
        //textview的点击事件

    }

    fun updateView(){
        var list = wordDao.allWords
        var text = ""
        for (i in list.indices) {
            // 使用 list[i] 进行操作
            val word =list.get(i)
            text +=  ""+word.id.plus(0) + ":" + word.word + "=" + word.chineseMeaning+"\n"
            binding.textView8.text = text
        }
    }
}