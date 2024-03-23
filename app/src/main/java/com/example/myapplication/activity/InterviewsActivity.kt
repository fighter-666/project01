package com.example.myapplication.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.InterviewsAdapter
import com.example.myapplication.databinding.ActivityInterviewsBinding
import com.example.myapplication.recharge.view.property.Piggy

/**
 * 面试活动界面，展示一个列表，可以添加或删除数据。
 */
class InterviewsActivity : AppCompatActivity() {
    // 视图绑定对象，用于访问布局中的视图
    private lateinit var binding: ActivityInterviewsBinding

    // 数据列表，存储Piggy对象
    private val dataList = mutableListOf<Piggy>()

    // 列表最大项数
    private val maxItems = 20

    // 适配器，用于RecyclerView的数据绑定
    private lateinit var interviewsAdapter: InterviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化视图绑定
        binding = ActivityInterviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化适配器并设置RecyclerView
        interviewsAdapter = InterviewsAdapter(R.layout.adapter_interviews, dataList)
        binding.rvInterviews.apply {
            layoutManager = LinearLayoutManager(this@InterviewsActivity)
            adapter = interviewsAdapter
        }

        // 初始化数据和监听器
        initData()
        initListener()
    }

    /**
     * 初始化数据，随机选择图片和文本资源填充列表。
     */
    private fun initData() {
        // 获取图片和文本资源数组
        val images = resources.obtainTypedArray(R.array.image_array)
        val texts = resources.getStringArray(R.array.text_array)
        // 记录已使用的资源索引，避免重复
        val usedImageIndices = mutableSetOf<Int>()
        val usedTextIndices = mutableSetOf<Int>()

        // 当列表项少于10个且资源未用完时，继续添加数据
        while (dataList.size < 10 && usedImageIndices.size < images.length() && usedTextIndices.size < texts.size) {
            // 随机选择一个未使用的图片索引
            var imageIndex: Int
            do {
                imageIndex = (0 until images.length()).random()
            } while (imageIndex in usedImageIndices)
            usedImageIndices.add(imageIndex)

            // 随机选择一个未使用的文本索引
            var textIndex: Int
            do {
                textIndex = texts.indices.random()
            } while (textIndex in usedTextIndices)
            usedTextIndices.add(textIndex)

            // 创建Piggy对象并添加到数据列表
            val imageRes = images.getResourceId(imageIndex, 0)
            val text = texts[textIndex]
            dataList.add(Piggy(imageRes, text))
        }

        // 回收TypedArray资源并通知适配器数据变化
        images.recycle()
        interviewsAdapter.notifyDataSetChanged()
    }

    /**
     * 初始化监听器，设置添加和删除按钮的点击事件。
     */
    private fun initListener() {
        // 添加按钮点击事件
        binding.tvAdd.setOnClickListener {
            if (dataList.size >= maxItems) {
                Toast.makeText(
                    this@InterviewsActivity,
                    "已满20行，不能再增加数据",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addData()
            }
        }

        // 删除按钮点击事件
        binding.tvDelete.setOnClickListener {
            deleteData()
        }
    }

    /**
     * 添加数据到列表。
     */
    private fun addData() {
        // 如果已达到最大项数，显示提示并返回
        if (dataList.size >= maxItems) {
            Toast.makeText(this, "已满20行，不能再增加数据", Toast.LENGTH_SHORT).show()
            return
        }

        // 获取图片和文本资源数组
        val images = resources.obtainTypedArray(R.array.image_array)
        val texts = resources.getStringArray(R.array.text_array)
        // 筛选出未使用的资源索引
        val availableImageIndices = (0 until images.length()).filter { index ->
            dataList.none { it.image == images.getResourceId(index, 0) }
        }.toMutableList()
        val availableTextIndices = (texts.indices).filter { index ->
            dataList.none { it.name == texts[index] }
        }.toMutableList()

        // 如果没有更多资源可添加，显示提示并返回
        if (availableImageIndices.isEmpty() || availableTextIndices.isEmpty()) {
            Toast.makeText(this, "没有更多的资源可以添加", Toast.LENGTH_SHORT).show()
            return
        }

// 随机选择一个未使用的图片索引
        val imageIndex = availableImageIndices.random()
// 随机选择一个未使用的文本索引
        val textIndex = availableTextIndices.random()

// 创建Piggy对象并添加到数据列表
        val imageRes = images.getResourceId(imageIndex, 0)
        val text = texts[textIndex]
        val newItem = Piggy(imageRes, text)

// 根据当前列表大小决定添加位置
        if (dataList.size < 3) {
            dataList.add(newItem)
        } else {
            dataList.add(2, newItem) // 在第3行新增数据
        }

// 回收TypedArray资源并通知适配器数据变化
        images.recycle()
        interviewsAdapter.notifyDataSetChanged()
    }

    /**
     * 从列表中删除数据。
     */
    private fun deleteData() {
        if (dataList.isEmpty()) {
            Toast.makeText(this, "列表为空，无法删除", Toast.LENGTH_SHORT).show()
            return
        }

        // 默认删除第二个项目，如果只有一个项目则删除它
        if (dataList.size == 1) {
            dataList.removeAt(0)
        } else {
            dataList.removeAt(1) // 删除第二行数据
        }

        // 通知适配器数据变化
        interviewsAdapter.notifyDataSetChanged()
    }
}