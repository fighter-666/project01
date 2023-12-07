package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.data.UserFluxPackageData
import com.example.myapplication.databinding.FragmentDataUsageBinding
import com.example.myapplication.widget.BaseLazyFragment
import com.google.gson.Gson


class DataUsageFragment : BaseLazyFragment() {
    private var _binding: FragmentDataUsageBinding? = null
    val binding get() = _binding!!
    private lateinit var mAdapter: TestAdapter
    private lateinit var mOverAdapter: TestAdapter
    private val multiItemEntities = mutableListOf<MultiItemEntity>()
    private val multiItemOverEntities = mutableListOf<MultiItemEntity>()

    companion object {
        private const val ARG_TAB_NAME = "tabName"
        fun newInstance(tabName: Int): DataUsageFragment {
            val args = Bundle()
            args.putString(ARG_TAB_NAME, tabName.toString())
            args.putInt(ARG_TAB_NAME, tabName)
            val fragment = DataUsageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDataUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun loadData() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        binding.recyclerOverView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        initAdapter()

    }

    private fun initAdapter() {
        //从应用程序的资产文件夹中读取名为"getFeedListData.json"的JSON文件并将其内容作为字符串进行处理
        val json: String =
            requireContext().assets.open("getUserFluxPackageData.json").bufferedReader()
                .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val fluxPackage = gson.fromJson(json, UserFluxPackageData::class.java)
        Log.d(
            "fluxPackageList",
            fluxPackage.productOFFRatable.exceedResourcePackages
                .toString()
        )


        mAdapter = TestAdapter(multiItemEntities)
        mOverAdapter = TestAdapter(multiItemOverEntities)

        generateData(fluxPackage.productOFFRatable.ratableResourcePackages)
        generateOverData(fluxPackage.productOFFRatable.exceedResourcePackages)

        binding.recyclerView.adapter = mAdapter
        binding.recyclerOverView.adapter = mOverAdapter
        //binding.recyclerView.isNestedScrollingEnabled = false
        val headerOverView = getOverHeaderView(
            0
        ) { mOverAdapter.addHeaderView(getOverHeaderView(1, getRemoveHeaderListener()), 0) }

        mOverAdapter.addHeaderView(headerOverView)

        val headerView = getHeaderView(
            0
        ) { mAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0) }
        mAdapter.addHeaderView(headerView)

        val footerView: View = getFooterView(0,
            View.OnClickListener {
                mAdapter.addFooterView(
                    getFooterView(
                        1,
                        getRemoveFooterListener()
                    ), 0
                )
            })
        mAdapter.addFooterView(footerView, 0)



        /*// 阈值提醒
        fluxPackage.warnInfo?.run {
            if (UtilText.isEmptyOrNull(title) && UtilText.isEmptyOrNull(describe)){
                binding.rlWarn.visibility = View.GONE
            }else{
                binding.rlWarn.visibility = View.VISIBLE
                *//*rlWarn.setOnVisibilityChange { view, isVisible ->
                    if (isVisible){
                        HgCxblExpose.exposeTips("流量", this)
                    }
                }*//*
            }

            if (UtilText.isEmptyOrNull(icon)) {
                binding.ivIcon.visibility = View.GONE
            } else {
                Glide.with(requireActivity())
                    .load(icon)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                    //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivIcon)
            }
            if (UtilText.isEmptyOrNull(title)) {
                binding.btnWarn.visibility = View.GONE
            } else {
                binding.btnWarn.visibility = View.VISIBLE
                binding.btnWarn.setOnClickListener {
                }
                binding.btnWarn.setText(title)
            }
            binding.tvWarnTitle.setText(describe)
        }*/

    }

    private fun getFooterView(type: Int, listener: View.OnClickListener): View {
        val view: View = layoutInflater.inflate(
            R.layout.recycleview_footer,
            binding.recyclerView.getParent() as ViewGroup,
            false
        )
        if (type == 1) {
            /*val imageView = view.findViewById<View>(R.id.iv) as ImageView
            imageView.setImageResource(R.mipmap.rm_icon)*/
        }
        //view.setOnClickListener(listener)
        return view
    }

    private fun getRemoveFooterListener(): View.OnClickListener {
        return View.OnClickListener { v -> mAdapter.removeFooterView(v) }
    }

    private fun getOverHeaderView(type: Int, listener: View.OnClickListener): View {
        val view: View = layoutInflater.inflate(
            R.layout.head_view,
            binding.recyclerOverView.getParent() as ViewGroup,
            false
        )
        if (type == 1) {
            /*val imageView = view.findViewById<View>(R.id.iv) as ImageView
            imageView.setImageResource(R.drawable.rm_icon)*/
        }
        //view.setOnClickListener(listener)
        return view
    }

    private fun getHeaderView(type: Int, listener: View.OnClickListener): View {
        val view: View = layoutInflater.inflate(
            R.layout.recyclview_head_over,
            binding.recyclerView.getParent() as ViewGroup,
            false
        )
        if (type == 1) {
            /*val imageView = view.findViewById<View>(R.id.iv) as ImageView
            imageView.setImageResource(R.drawable.rm_icon)*/
        }
        //view.setOnClickListener(listener)
        return view
    }

    private fun getRemoveHeaderListener(): View.OnClickListener {
        return View.OnClickListener { v -> mAdapter.removeHeaderView(v) }
    }


    /*这段代码是一个 Kotlin 函数，名为 generateData，它接受一个参数 item，类型为 List<UserFluxPackageData.
    ProductOFFRatableBean.RatableResourcePackagesBean>。这个函数的作用是根据传入的数据生成特定的数据结构，
    并更新 RecyclerView 的显示。
在函数中，首先通过 item.size 获取了列表 item 的大小，并存储在变量 lv0Count 中。
然后使用一个循环遍历 item 列表中的每个元素，假设当前元素为 lv0。
在内部的循环中，对于每个 lv0 元素，通过 lv0.productInfos.size 获取了其子元素（假设是 lv1）列表的大小，
并存储在变量 lv1Count 中。然后再次使用循环遍历 lv0 元素中的每个 lv1 子元素，并将其添加到 lv0 中。

最后，将经过处理的 lv0 元素添加到 multiItemEntities 列表中。在所有数据处理完成后，
如果 multiItemEntities 列表的大小大于0，则会执行一些操作（在代码中被注释掉了），最后调用
 mAdapter.notifyDataSetChanged() 来通知适配器数据集发生了变化，需要进行更新显示。*/
    private fun generateData(item: List<UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean>) {
        val lv0Count = item.size
        for (i in 0 until lv0Count) {
            val lv0 = item[i]
            val lv1Count = lv0.productInfos.size
            for (j in 0 until lv1Count) {
                val lv1 = lv0.productInfos[j]
                lv0.addSubItem(lv1)
            }
            multiItemEntities.add(lv0)
        }

        if (multiItemEntities.size > 0) {
            //llMealNormal.visibility = View.VISIBLE
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun generateOverData(item: List<UserFluxPackageData.ProductOFFRatableBean.RatableResourcePackagesBean>) {
        val lv0Count = item.size
        for (i in 0 until lv0Count) {
            val lv0 = item[i]
            val lv1Count = lv0.productInfos.size
            for (j in 0 until lv1Count) {
                val lv1 = lv0.productInfos[j]
                lv0.addSubItem(lv1)
            }
            multiItemOverEntities.add(lv0)
        }

        if (multiItemOverEntities.size > 0) {
            //llMealNormal.visibility = View.VISIBLE
        }
        mOverAdapter.notifyDataSetChanged()
    }

}


