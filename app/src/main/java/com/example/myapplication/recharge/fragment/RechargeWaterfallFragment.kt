package com.example.myapplication.recharge.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.recharge.adapter.RechargeWaterfallMultipleItemQuickAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.google.gson.Gson


class RechargeWaterfallFragment : Fragment() {
    private var _binding: FragmentRechargeWaterfallBinding? = null
    val binding get() = _binding!!
    private lateinit var myAdapter: RechargeWaterfallMultipleItemQuickAdapter
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRechargeWaterfallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json: String = requireContext().assets.open("waterfalldata.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val feedList= gson.fromJson(json, GetFeedListData::class.java)
        val tag = "TAG"
        for (feed in feedList.feedList) {
            Log.d(tag,"标签名称: ${feed.type}")
        }

        myAdapter = RechargeWaterfallMultipleItemQuickAdapter( feedList.feedList)
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
        }
        myAdapter.setOnLoadMoreListener(object :
            RechargeWaterfallMultipleItemQuickAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                // 执行加载更多的逻辑

            }
        })


        /*mSwipeRefreshLayout = binding.srl
        mSwipeRefreshLayout.setOnRefreshListener { //我在List最前面加入一条数据



            //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
            //myAdapter.notifyDataSetChanged()
            mSwipeRefreshLayout.setRefreshing(false)
        }*/

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { contactUri ->
                val contactNumber = getContactNumberByUri(contactUri)
                myAdapter.onContactClickListener?.onContactClick(contactNumber)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getContactNumberByUri(contactUri: Uri): String? {
        val contactData = contactUri
        var phoneNumber: String? = null
        val cursor = requireActivity().contentResolver.query(contactData, null, null, null, null)
        cursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val hasPhoneIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)

                val name = cursor.getString(nameIndex)
                var hasPhone = cursor.getString(hasPhoneIndex)
                val id = cursor.getString(idIndex)

                hasPhone = if (hasPhone.equals("1", ignoreCase = true)) {
                    "true"
                } else {
                    "false"
                }

                if (hasPhone.toBoolean()) {
                    val phonesCursor = requireActivity().contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null,
                        null
                    )

                    phonesCursor?.use { phonesCursor ->
                        if (phonesCursor.moveToFirst()) {
                            val phoneNumberIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            phoneNumber = phonesCursor.getString(phoneNumberIndex)
                        }
                    }
                }
            }
        }

        return phoneNumber
    }
}


