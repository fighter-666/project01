package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWebViewX5Binding


class WebViewX5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewX5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewX5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val webSetting =  binding.WebViewX5.settings
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportZoom(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setDomStorageEnabled(true);
        binding.WebViewX5.loadUrl("https://www.baidu.com")

        //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
        Log.d("app", " onViewInitFinished is ${binding.WebViewX5}")

//        QbSdk.initX5Environment(applicationContext, object : PreInitCallback {
//            override fun onCoreInitFinished() {
//                // 内核初始化完成，可能为系统内核，也可能为系统内核
//            }
//
//            /**
//             * 预初始化结束
//             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
//             * @param isX5 是否使用X5内核
//             */
//            override fun onViewInitFinished(isX5: Boolean) {}
//        })
//        QbSdk.setDownloadWithoutWifi(true)
//
//        // 在调用TBS初始化、创建WebView之前进行如下配置
//        // 在调用TBS初始化、创建WebView之前进行如下配置
//        val map = HashMap<String, Any?>()
//        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] =
//            true
//        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] =
//            true
//        QbSdk.initTbsSettings(map)




//        val strategy = UserStrategy(applicationContext)
//        strategy.setCrashHandleCallback(object : CrashReport.CrashHandleCallback() {
//            override fun onCrashHandleStart(
//                crashType: Int,
//                errorType: String?,
//                errorMessage: String?,
//                errorStack: String?
//            ): Map<String, String> {
//                val map = linkedMapOf<String, String>()
//                val x5CrashInfo = com.tencent.smtt.sdk.WebView.getCrashExtraMessage(appContext)
//                map["x5crashInfo"] = x5CrashInfo
//                return map
//            }
//
//            override fun onCrashHandleStart2GetExtraDatas(
//                crashType: Int,
//                errorType: String?,
//                errorMessage: String?,
//                errorStack: String?
//            ): ByteArray? {
//                return try {
//                    "Extra data.".toByteArray(charset("UTF-8"))
//                } catch (e: Exception) {
//                    null
//                }
//            }
//        })
//
//        CrashReport.initCrashReport(appContext, APPID, true, strategy)
    }
}