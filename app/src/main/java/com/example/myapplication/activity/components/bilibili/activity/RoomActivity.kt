package com.example.myapplication.activity.components.bilibili.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.activity.components.bilibili.data.Word
import com.example.myapplication.activity.components.bilibili.data.WordDao
import com.example.myapplication.activity.components.bilibili.data.WordDatabase
import com.example.myapplication.databinding.ActivityRoomBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var wordDao: WordDao
    private lateinit var wordDatabase: WordDatabase
    private lateinit var allWordsLive: LiveData<List<Word>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordDatabase = Room.databaseBuilder(this,WordDatabase::class.java,"word_datebase")
            .allowMainThreadQueries()
            .build()
        wordDao = wordDatabase.wordDao
        allWordsLive = wordDao.allWordsLive
        allWordsLive.observe(this, Observer<List<Word>> { words ->
            // 在 onChanged 方法中处理数据变化
            var text = ""
            for (i in words.indices) {
                // 使用 list[i] 进行操作
                val word =words.get(i)
                text +=  ""+word.id.plus(0) + ":" + word.word + "=" + word.chineseMeaning+"\n"
                binding.textView8.text = text
            }
        })

        binding.btInsert.setOnClickListener {
            val word1 = Word("hello","您好！")
            val word2 = Word("world","世界！")
            wordDao.insertWords(word1,word2)
        }
        binding.btClear.setOnClickListener {
            wordDao.deleteAllWords()
        }
        binding.btUpdate.setOnClickListener {
            val word = Word("Hi","哇哈哈")
            word.id = 224
            wordDao.updateWords(word)
        }
        binding.btDelete.setOnClickListener {
            val word = Word("Hi","哇哈哈")
            word.id = 226
            wordDao.deleteWords(word)
        }
        //textview的点击事件

    }

    class InsertAsyncTask(private val wordDao: WordDao) {

        //使用 suspend 关键字来表示挂起函数，协程通过 Dispatchers 来确定在哪个线程池中执行任务
        suspend fun doInBackground(vararg words: Word) {
            withContext(Dispatchers.IO) {
                wordDao.insertWords(*words)
            }
        }
    }

    class UpdateAsyncTask(private val wordDao: WordDao) {

        //使用 suspend 关键字来表示挂起函数，协程通过 Dispatchers 来确定在哪个线程池中执行任务
        suspend fun doInBackground(vararg words: Word) {
            withContext(Dispatchers.IO) {
                wordDao.updateWords(*words)
            }
        }
    }

    class DeleteAsyncTask(private val wordDao: WordDao) {

        //使用 suspend 关键字来表示挂起函数，协程通过 Dispatchers 来确定在哪个线程池中执行任务
        suspend fun doInBackground(vararg words: Word) {
            withContext(Dispatchers.IO) {
                wordDao.deleteWords(*words)
            }
        }
    }

    class DeleteAllAsyncTask(private val wordDao: WordDao) {

        //使用 suspend 关键字来表示挂起函数，协程通过 Dispatchers 来确定在哪个线程池中执行任务
        suspend fun doInBackground(vararg void: Void) {
            withContext(Dispatchers.IO) {
                wordDao.deleteAllWords()
            }
        }
    }

}