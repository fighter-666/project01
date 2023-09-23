package com.example.myapplication.recharge.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.databinding.FragmentRechargeWaterfallBinding
import com.example.myapplication.recharge.adapter.WaterfallAdapter
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.recharge.widget.MyLifecycleObserver
import com.google.gson.Gson


class WaterfallFragment : Fragment() {
    private var _binding: FragmentRechargeWaterfallBinding? = null
    val binding get() = _binding!!
    private lateinit var myAdapter: WaterfallAdapter
    private var contactNumber: String? = null
    private var mIntent: Intent? = null
    private lateinit var feedList: GetFeedListData
    private lateinit var observer: MyLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ...

        observer = MyLifecycleObserver(requireActivity().activityResultRegistry)
        lifecycle.addObserver(observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRechargeWaterfallBinding.inflate(inflater, container, false)
        return binding.root
    }

  /*  private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // 用户授予了权限
            // 在这里执行相应的操作
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            pickContactLauncher.launch(intent)
        } else {
            // 用户拒绝了权限
            // 在这里处理权限被拒绝的情况
        }
    }*/
    private val pickContactLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Log.d("aaa",result.resultCode.toString())
            if (result.resultCode == Activity.RESULT_OK) {
                mIntent = result.data
                val contactUri = mIntent?.data
                contactNumber = getContactNumberByUri(contactUri)
                //刷新指定item
                val updatedItem = myAdapter.getItem(1)
                if (updatedItem.quickRecharge != null) {
                    updatedItem.quickRecharge.title = contactNumber
                    // 更新适配器中的数据集
                    feedList.feedList[1] = updatedItem // 将索引为2的项替换为更新后的项
                    myAdapter.notifyItemChanged(1)
                }

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json: String = requireContext().assets.open("getFeedListData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        feedList = gson.fromJson(json, GetFeedListData::class.java)
        //初始化瀑布流
        myAdapter = WaterfallAdapter(feedList.feedList)
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = myAdapter
        }
        myAdapter.setOnItemClickListener {
            if (context?.let { it1 ->
                    ContextCompat.checkSelfPermission(
                        it1, Manifest.permission.READ_CONTACTS
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                // 如果没有权限，则向用户请求权限
                ActivityCompat.requestPermissions(
                    context as Activity, arrayOf(Manifest.permission.READ_CONTACTS), 2
                )

            } else {
                // 如果已经拥有权限，则执行读取联系人数据的操作
                getContactNumberByUri(mIntent?.data)
            }
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            pickContactLauncher.launch(intent)
        }

        binding.btnSelect.setOnClickListener {
            // Open the activity to select an image
            observer.selectImage()
        }

        /* if (ContextCompat.checkSelfPermission(
                 requireContext(), Manifest.permission.READ_CONTACTS
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             // 如果没有权限，则向用户请求权限
             ActivityCompat.requestPermissions(
                 requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 2
             )
         } else {
             // 如果已经拥有权限，则执行读取联系人数据的操作
             getContactNumberByUri(contactUri)
         }*/
        //requestPermission

    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_CONTACT -> {
                mIntent = data
                val contactUri = data?.data
                getContactNumberByUri(contactUri)
                if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        mIntent = data
                        contactNumber = getContactNumberByUri(contactUri)

                        //刷新指定item
                        val updatedItem = myAdapter.getItem(1)
                        if (updatedItem.quickRecharge != null) {
                            updatedItem.quickRecharge.title = contactNumber
                            // 更新适配器中的数据集
                            feedList.feedList[1] = updatedItem // 将索引为2的项替换为更新后的项
                            myAdapter.notifyItemChanged(1)
                        }
                    }
                }
            }
        }
    }*/

    /*    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray,
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 2) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户已经授予了读取联系人的权限
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    pickContactLauncher.launch(intent)
                } else {
                    // 用户拒绝了权限请求，可以在这里处理相应逻辑
                    Toast.makeText(context,"权限被拒绝了",Toast.LENGTH_SHORT).show();
                }
            }
        }*/

    //用于从联系人的 Uri（Uniform Resource Identifier，统一资源标识符）中获取联系人的电话号码
    private fun getContactNumberByUri(data: Uri?): String? {

        var phoneNumber: String? = null
        //来获取一个光标（Cursor）对象。这里使用 contentResolver 来查询联系人的数据
        val cursor =
            data?.let { requireActivity().contentResolver.query(it, null, null, null, null) }
        //使用 Kotlin 的 use 函数，确保在使用完光标后关闭它
        cursor?.use { it ->
            //这行代码检查 Cursor 是否有数据，并将 Cursor 定位到第一行
            if (it.moveToFirst()) {
                //这行代码获取存储联系人是否有电话号码的列的索引。
                // HAS_PHONE_NUMBER 是联系人表中的一个列，它表示该联系人是否有电话号码。
                val hasPhoneIndex = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                //这行代码获取联系人 ID 列的索引。_ID 是联系人表中的一个列，它表示每个联系人的唯一标识符。
                val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)

                //这行代码从 Cursor 中获取存储联系人是否有电话号码的值，并将其存储在名为 hasPhone 的变量中。
                var hasPhone = it.getString(hasPhoneIndex)
                val id = it.getString(idIndex)

                // 这行代码将 hasPhone 的值进行比较。如果它等于字符串 "1"（忽略大小写），
                // 则将 hasPhone 设置为 "true"，否则设置为 "false"。
                // 这样做是为了将数据库中存储的 1/0 值转换为更易读的布尔值。
                hasPhone = if (hasPhone.equals("1", ignoreCase = true)) {
                    "true"
                } else {
                    "false"
                }

                if (hasPhone.toBoolean()) {
                    //这行代码使用 contentResolver 查询联系人的电话号码。
                    // requireActivity() 返回与当前 Fragment 相关联的 Activity。
                    val phonesCursor = requireActivity().contentResolver.query(
                        //表示查询电话号码的内容 URI，指定了查询电话号码的数据表。
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        //表示查询条件，限制查询结果必须与给定的联系人 ID 相匹配。
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null
                    )

                    // 这行代码使用了安全调用操作符 ?. 以确保 phonesCursor 不为 null，并在使用后自动关闭 Cursor
                    phonesCursor?.use {
                        //这行代码检查 Cursor 是否有数据，并将 Cursor 定位到第一行
                        if (it.moveToFirst()) {
                            //获取电话号码列的索引，将其赋值给 phoneNumberIndex。
                            val phoneNumberIndex =
                                it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            //通过索引从光标中获取电话号码，并将其赋值给 phoneNumber
                            phoneNumber = it.getString(phoneNumberIndex)

                        }
                    }
                }
            }
        }
        return phoneNumber
    }

    /*companion object {
        private const val PICK_CONTACT = 1
    }*/
}


