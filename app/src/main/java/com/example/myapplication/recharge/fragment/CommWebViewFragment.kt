package com.example.myapplication.recharge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.activity.components.RechargePageActivity
import com.example.myapplication.databinding.FragmentRechargeWaterfallBaiduBinding
import com.example.myapplication.widget.BaseLazyFragment


class CommWebViewFragment : BaseLazyFragment() {
    private var _binding: FragmentRechargeWaterfallBaiduBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRechargeWaterfallBaiduBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun loadData() {
        //访问网页
        binding.webview.post {
            //binding.webview.settings.setJavaScriptEnabled(true)
            binding.webview.loadUrl(RechargePageActivity.link)
            //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
            binding.webview.webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    //使用WebView加载显示url
                    view.loadUrl(url)
                    //返回true
                    return true
                }
            }
        }
    }

    companion object {
        private const val ARG_TAB_NAME = "tabName"

        fun newInstance(tabName: Int): CommWebViewFragment {
            val args = Bundle()
            args.putString(ARG_TAB_NAME, tabName.toString())
            val fragment = CommWebViewFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


