package com.example.myapplication.activity.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class PhnoeActivity : AppCompatActivity(), View.OnClickListener {
    private var et_phone: TextView? = null
    private var btn_select: ImageView? = null
    private var mIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phnoe)
        et_phone = findViewById<TextView>(R.id.et_phone)
        btn_select = findViewById(R.id.btn_select)
        btn_select?.setOnClickListener(this)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PICK_CONTACT -> {
                mIntent = data
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    //申请授权，第一个参数为要申请用户授权的权限；第二个参数为requestCode 必须大于等于0，主要用于回调的时候检测，匹配特定的onRequestPermissionsResult。
                    //可以从方法名requestPermissions以及第二个参数看出，是支持一次性申请多个权限的，系统会通过对话框逐一询问用户是否授权。
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        PERMISSIONS_REQUEST_READ_CONTACTS
                    )
                } else {
                    //如果该版本低于6.0，或者该权限已被授予，它则可以继续读取联系人。
                    getContacts(data)
                }
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户成功授予权限
                getContacts(mIntent)
            } else {
                Toast.makeText(this, "你拒绝了此应用对读取联系人权限的申请！", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onClick(view: View) {
        selectConnection()
    }

    private fun selectConnection() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, PICK_CONTACT)
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
        private const val PICK_CONTACT = 1
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 2
    }
}