package com.example.myapplication.recharge.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.activity.components.PhnoeActivity
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.recharge.adapter.RechargeWaterfallMultipleItemQuickAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.google.gson.Gson


class RechargeWaterfallFragment : Fragment(){
    private var _binding: FragmentRechargeWaterfallBinding? = null
    val binding get() = _binding!!
    private lateinit var myAdapter: RechargeWaterfallMultipleItemQuickAdapter
    private  var contactNumber: String? = null
    private var mIntent: Intent? = null
    private lateinit var feedList: GetFeedListData

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
        feedList= gson.fromJson(json, GetFeedListData::class.java)
        //初始化瀑布流
        myAdapter = RechargeWaterfallMultipleItemQuickAdapter( feedList.feedList)
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_CONTACT -> {
                mIntent = data
                val contactUri = data?.data
                getContactNumberByUri(contactUri)
                if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                    data?.data?.let { contactUri ->
                        mIntent = data
                        contactNumber = getContactNumberByUri(contactUri)

                        val updatedItem =
                            myAdapter.getItem(1)
                        if (updatedItem.quickRecharge != null) {
                            updatedItem.quickRecharge.title = contactNumber
                            // 更新适配器中的数据集
                            feedList.feedList.set(1, updatedItem); // 将索引为2的项替换为更新后的项
                            myAdapter.notifyItemChanged(1)
                            LogUtils.d(
                                "contactNumber=" + contactNumber + "; updatedItem=" + updatedItem.quickRecharge.title
                            )
                        }


                    }
                }
            }


        }

    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val contactUri = data.data
                val phoneNumber = contactUri?.let { getContactNumberByUri(it) }
                // 在这里处理获取的选中手机号码 phoneNumber
                LogUtils.d(
                    "phoneNumber=" + phoneNumber
                )
            }
        }
    }*/




    //用于从联系人的 Uri（Uniform Resource Identifier，统一资源标识符）中获取联系人的电话号码
    private fun getContactNumberByUri(data: Uri?): String? {

        var phoneNumber: String? = null
        val contactUri = data
        //来获取一个光标（Cursor）对象。这里使用 contentResolver 来查询联系人的数据
        val cursor =
            contactUri?.let { requireActivity().contentResolver.query(it, null, null, null, null) }
        //使用 Kotlin 的 use 函数，确保在使用完光标后关闭它
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
                        //表示查询电话号码的内容 URI，指定了查询电话号码的数据表。
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        //表示查询条件，限制查询结果必须与给定的联系人 ID 相匹配。
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null,
                        null
                    )

                    phonesCursor?.use { phonesCursor ->
                        if (phonesCursor.moveToFirst()) {
                            //获取电话号码列的索引，将其赋值给 phoneNumberIndex。
                            val phoneNumberIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            //通过索引从光标中获取电话号码，并将其赋值给 phoneNumber
                            phoneNumber = phonesCursor.getString(phoneNumberIndex)

                        }
                    }
                }
            }
        }
       return phoneNumber
    }

    companion object {
        private const val PICK_CONTACT = 1
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 2
    }


}


