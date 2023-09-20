package com.example.myapplication.activity.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R


class PhoneActivity : AppCompatActivity() {
    private var et_phone: TextView? = null
    private var btn_select: ImageView? = null
    private var mIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phnoe)
        et_phone = findViewById<TextView>(R.id.et_phone)
        btn_select = findViewById(R.id.btn_select)
        btn_select?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(intent, PICK_CONTACT)
        }
        // 检查是否已经拥有所需权限
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // 如果没有权限，则向用户请求权限
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 2)
        } else {
            // 如果已经拥有权限，则执行读取联系人数据的操作
            getContacts(mIntent)
        }

        // 处理权限请求结果

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PICK_CONTACT -> {
                mIntent = data
                    getContacts(data)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PICK_CONTACT) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户已经授予了读取联系人的权限
                getContacts(mIntent)
            } else {
                // 用户拒绝了权限请求，可以在这里处理相应逻辑
            }
        }
    }




    @SuppressLint("Range")
    private fun getContacts(data: Intent?) {
        if (data == null) {
            return
        }
        var phoneNumber = ""
        val contactUri = data.data
        val cursor = contentResolver.query(contactUri!!, null, null, null, null)
        if (cursor!!.moveToFirst()) {
            var hasPhone = cursor
                .getString(
                    cursor
                        .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                )
            val id = cursor.getString(
                cursor
                    .getColumnIndex(ContactsContract.Contacts._ID)
            )
            hasPhone = if (hasPhone.equals("1", ignoreCase = true)) {
                "true"
            } else {
                "false"
            }
            if (hasPhone.toBoolean()
            ) {
                val phones = contentResolver
                    .query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + id, null, null
                    )
                while (phones!!.moveToNext()) {
                    phoneNumber = phones
                        .getString(
                            phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                }
                phones.close()
            }
            cursor.close()
            et_phone!!.setText(phoneNumber)
        }
    }

    companion object {
        const val PICK_CONTACT = 1
    }
}