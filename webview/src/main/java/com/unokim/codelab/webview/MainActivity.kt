package com.unokim.codelab.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.unokim.codelab.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web_view.settings.javaScriptEnabled = true
        web_view.webChromeClient = WebChromeClient()
        web_view.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Logger.i(TAG, "shouldOverrideUrlLoading, url = $url")
                view?.loadUrl(url)
                return true
            }

//            override fun onPageFinished(view: WebView?, url: String?) {
//                Logger.i(TAG, "onPageFinished")
//
//                val script = """
//                    javascript:function afterLoad() {
//                        document.getElementById('query').value = '오늘 날씨';
//                        document.forms["search"].submit();
//                    };
//                    afterLoad();
//                """
//                view?.loadUrl(script)
//            }
        }
        web_view.loadUrl("https://m.naver.com")


        btn_search.setOnClickListener {
            val text = input.text.toString()
            val script = """
                    javascript:function afterLoad() {
                        document.getElementById('query').value = '$text';
                        document.forms["search"].submit();
                    };
                    afterLoad();
                """
            web_view.loadUrl(script)
        }
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
            return
        }
        super.onBackPressed()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
