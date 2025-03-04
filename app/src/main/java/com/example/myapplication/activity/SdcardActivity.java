package com.example.myapplication.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.databinding.ActivityRawBinding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import android.Manifest;
import android.widget.Toast;

public class SdcardActivity extends AppCompatActivity{
    private ActivityRawBinding binding;
    private static final String TAG = "SdcardActivity";

    public static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "请开通相关权限，否则无法正常使用！", Toast.LENGTH_SHORT).show();
                Log.d(TAG , "AonRequestPermissionsResult checkPermission 否则无法正常使用");
            }
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        } else {
            Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.d(TAG , "AonRequestPermissionsResult  checkPermission");
            writeToFile("娃哈哈娃哈哈");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                    Log.d(TAG , "AonRequestPermissionsResult  checkPermission 授权成功");
                    writeToFile("娃哈哈娃哈哈");

                } else {
                    Toast.makeText(this, "授权被拒绝", Toast.LENGTH_SHORT).show();
                    Log.d(TAG , "AonRequestPermissionsResult  checkPermission 授权被拒绝");
                }
                return;
            }
        }
    }

    private void readAdcard() throws FileNotFoundException {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                InputStream inputStream = null;
                Reader reader = null;
                BufferedReader bufferedReader = null;
                try {
                    File storage = Environment.getExternalStorageDirectory();
                    File tempfile = new File(storage.getPath());
                    File file = new File(tempfile, "test.txt");
                    inputStream = new FileInputStream(file);
                    reader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(reader);
                    StringBuilder result = new StringBuilder();
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null) {
                        result.append(temp);
                    }
                    Log.d(TAG, "readAdcard: result" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
    }
    public void writeToFile(String content) {
        Log.d(TAG , "AonRequestPermissionsResult init");
        FileOutputStream outputStream = null;
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
//                File storage = Environment.getExternalStorageDirectory();
            File reserveDir = new File("Reserve0");

            // 检查 Reserve0 目录是否存在，若不存在则创建
            if (!reserveDir.exists()) {
                if (reserveDir.mkdirs()) {
                    Log.d(TAG ,"Reserve0 目录创建成功。");
                } else {
                    Log.d(TAG ,"Reserve0 目录创建失败。");
                    return;
                }
            }

            // 创建代表 txz 文件的 File 对象，该文件位于 Reserve0 目录下
            File txzFile = new File(reserveDir, "txz");
            try {
                // 尝试创建 txz 文件
                if (txzFile.createNewFile()) {
                    Log.d(TAG ,"txz 文件创建成功。");
                } else {
                    Log.d(TAG ,"txz 文件可能已存在，创建失败。");
                }
            } catch (IOException e) {
                Log.d(TAG ,"创建 txz 文件时出错: " + e.getMessage());
            }
            File file = new File(reserveDir, "txz");
            outputStream = new FileOutputStream(file);
            writer = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
            Log.d(TAG , "AonRequestPermissionsResult successs");
        } catch (Exception e) {
//                e.printStackTrace();
            Log.d(TAG , "Content written to file false" + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG , "Content written to file  final false" + e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPermission();

    }

}
