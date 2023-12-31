package com.example.myapplication.activity.components

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.PersonTest
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

        val person = PersonTest()
        person.name ="Tom"
        person.age = 21
        binding.btnAdd.setOnClickListener {
            /*val db =dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                //开始组装第一条数据
                put("name", "Android")
                put("author", "Android")
                put("pages",454)
                put("price", 123.45)
            }
            db.insert("Book", null, values1)*/
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("person_data",person)
            startActivity(intent)
        }
    }
}