package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityRawBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataActivity extends AppCompatActivity{
    private ActivityRawBinding binding;
    private static final String TAG = "AssetsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       String text = "hello world";
       File file = new File("data/data/com.example.myapplication/","test.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream  = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
