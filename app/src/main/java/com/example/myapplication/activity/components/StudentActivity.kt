package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.Student
import com.example.myapplication.util.StudentViewModel

class StudentActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var tvMessage:TextView
    private lateinit var button: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        tvMessage = findViewById(R.id.tvMessage)
        button = findViewById(R.id.btnConfirm)
        editText = findViewById(R.id.edittext)
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        studentViewModel.newScore.observe(this, Observer{
            tvMessage.text = "分数：$it"
        })

        button.setOnClickListener {
            studentViewModel.setStudentId(editText.text.toString().trim())
        }

      /*  button.setOnClickListener {
            studentViewModel.getScore(editText.text.toString().trim()).observe(this, Observer{
                tvMessage.text = "分数：$it"
            })
        }*/
     /*   val student = Student("WuDaoman","123456",99)
        studentViewModel.setStudentMessage(student)
        studentViewModel._student.observe(this, Observer {
            Log.d("StudentActivity:" , it.toString())
            tvMessage.text = "score:"+ it
        })*/

    }
}