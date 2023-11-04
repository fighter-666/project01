package com.example.myapplication.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Student

class StudentViewModel : ViewModel() {
    private var studentLiveData = MutableLiveData<Student>()
    private var studentIdLiveData = MutableLiveData<String>()

    /*
    * 设置学生id
    * @param student 学生 id
    * */
    fun setStudentId(studentId: String) {
        studentIdLiveData.value = studentId
    }

   /* val newScore: LiveData<Int> = Transformations.switchMap(studentIdLiveData, object : Function<String, LiveData<Int>> {
        override fun apply(input: String): LiveData<Int> {
            return StudentRespository().getStudentScore(input)
        }
    })*/

    var newScore: LiveData<Int> = Transformations.switchMap(studentIdLiveData) {
        StudentRespository().getStudentScore(it)

    }

    /*val _student: LiveData<Student>
        get() = studentLiveData*/
    val _student: LiveData<Int>
        //利用map转化的作用，把liveData的值转换成int的实例
        get() = Transformations.map(studentLiveData) {
            it.score
        }

    fun setStudentMessage(student: Student) {
        studentLiveData.value = student
    }

    /*
    * 获取分数
    * @param id 学生 id
    * */
    fun getScore(id: String): LiveData<Int> {
        return StudentRespository().getStudentScore(id)
    }
}