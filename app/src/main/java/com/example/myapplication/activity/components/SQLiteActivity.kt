package com.example.myapplication.activity.components

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySqliteBinding
import com.example.myapplication.sqiite2.MyDatabaseHelper

class SQLiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySqliteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper = MyDatabaseHelper(this, "Book.db", 2)
        binding.btCreateDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        binding.btnAdd.setOnClickListener {
            val db =dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                //开始组装第一条数据
                put("name", "Android")
                put("author", "Android")
                put("pages",454)
                put("price", 123.45)
            }
            db.insert("Book", null, values1)
        }
    }
}