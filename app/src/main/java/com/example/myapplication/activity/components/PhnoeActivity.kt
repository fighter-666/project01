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


class PhnoeActivity : AppCompatActivity() {
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
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 2
    }
}