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
    private val multiItemEntities = mutableListOf<MultiItemEntity>()

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
            fluxPackage.productOFFRatable.ratableResourcePackages[0].title.toString()
        )


        mAdapter = TestAdapter(multiItemEntities)
        generateData(fluxPackage.productOFFRatable.ratableResourcePackages)
        binding.recyclerView.adapter = mAdapter
        //binding.recyclerView.isNestedScrollingEnabled = false
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

    private fun getHeaderView(type: Int, listener: View.OnClickListener): View {
        val view: View = layoutInflater.inflate(
            R.layout.head_view,
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

}


