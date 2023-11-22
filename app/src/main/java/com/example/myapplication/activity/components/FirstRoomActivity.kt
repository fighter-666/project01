package com.example.myapplication.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.MyApplication
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.databinding.ActivityFirstRoomBinding
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.User
import com.example.myapplication.room2.DbManager
import com.example.myapplication.room2.UserDao
import kotlin.concurrent.thread
import kotlin.random.Random

class FirstRoomActivity : AppCompatActivity() {
    private val TagL = FirstRoomActivity::class.java.simpleName
    private lateinit var binding: ActivityFirstRoomBinding

    private lateinit var resultRecycle: RecyclerView
    private var list = mutableListOf<com.example.myapplication.room2.User>()
    var getUserDao: UserDao = DbManager.db.userDao()
    private val userAdapter by lazy {
        UserAdapter(R.layout.adapter_user_item_layout)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.recyclerview.adapter = userAdapter
        findVbId()

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.d("FirstRoomActivity",user.toString())
                }
            }
        }
    }

    private fun findVbId() {
        binding.query.setOnClickListener { query() }
        binding.insert.setOnClickListener { insertSingle() }
        binding.delete.setOnClickListener { delete() }
        binding.update.setOnClickListener { update() }
        userAdapter.setOnItemChildClickListener { _, view, position ->
            when(view.id){
                R.id.item_delete ->singleDel(list[position])
                R.id.item_modify ->singleModify(list[position])
            }
        }
        insertAll()
    }

    /*
    * 查询
    * */
    private fun query(){
        list = getUserDao.queryAllUser()
        Log.e(TagL, list.toString())
        userAdapter
            .setNewData(list)
    }

    /*
    * 默认插入批量数据
    * */
    private fun insertAll(){
        if(getUserDao.queryAllUser().size == 0){
            val mutableList: MutableList<com.example.myapplication.room2.User> = mutableListOf()
            for (a in 1..3){
                val user = com.example.myapplication.room2.User("张三$a",20+a,"贵阳市$a","")
                mutableList.add(user)
            }
            getUserDao.addBatchUser(mutableList)
            Toast.makeText(MyApplication.getContext(),"批量新增数据成功",Toast.LENGTH_SHORT).show()
        }
        query()
    }

    /*
    * 插入单挑数据
    * */
    private fun insertSingle(){
        val age = Random.nextInt(20,40)
        val user = com.example.myapplication.room2.User("小二",age,"贵阳市aaaa","")
        getUserDao.addUser(user)
        Toast.makeText(MyApplication.getContext(),"新增一条数据成功",Toast.LENGTH_SHORT).show()
        query()
    }

    /*
    * 删除表里所有数据
    * */
    private fun delete(){
        getUserDao.deleteAllUser()
        Toast.makeText(MyApplication.getContext(),"删除表里所有数据成功",Toast.LENGTH_SHORT).show()
        query()
    }

    /*
    * 更新所有数据
    * */
    private fun update(){
        getUserDao.updateAll()
        Toast.makeText(MyApplication.getContext(),"更新所有数据成功",Toast.LENGTH_SHORT).show()
        query()
    }

    /*删除loginUser表里的指定删除某一个
    * */
    private fun singleDel(singleUser: com.example.myapplication.room2.User){
        getUserDao.deleteSingle(singleUser)
        Toast.makeText(MyApplication.getContext(),"删除指定数据成功",Toast.LENGTH_SHORT).show()
        query()
    }

    /*
    * 修改数据表某一个对象的字段值
    * */
    private fun singleModify(user: com.example.myapplication.room2.User){
        user.aliasName = "修改的" + user.aliasName
        user.age = 100
        user.ads = "修改的地址白云区"
        getUserDao.updateUser(user)
        Toast.makeText(MyApplication.getContext(),"修改数据表某一个对象的字段值成功",Toast.LENGTH_SHORT).show()
        list.clear()
        query()
    }

}