package com.mobbelldev.kophi.presentation.ui.coffee.payment

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.mobbelldev.kophi.BuildConfig
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityPaymentBinding
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.CheckoutActivity
import com.mobbelldev.kophi.presentation.ui.transaction.TransactionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.webView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webView = binding.webView
        setupWebView(webView = webView)

        val snapUrl = intent.getStringExtra(CheckoutActivity.EXTRA_URL_SNAP)
        val snapUrlTokenId = intent.getStringExtra(TransactionFragment.SNAP_URL_TOKEN_ID)
        val url = when {
            snapUrl?.isNotEmpty() == true -> snapUrl
            snapUrlTokenId?.isNotEmpty() == true -> "${BuildConfig.SNAP_URL}$snapUrlTokenId"
            else -> {
                null
            }
        }

        // Logging untuk debugging
        Log.d("PaymentActivity", "snapUrl: $snapUrl")
        Log.d("PaymentActivity", "snapUrlTokenId: $snapUrlTokenId")
        Log.d("PaymentActivity", "Final URL: $url")

        if (savedInstanceState == null) {
            url?.let { webView.loadUrl(it) }
        }
    }

    private fun setupWebView(webView: WebView) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            allowContentAccess = true
            allowFileAccess = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("PaymentActivity", "WebView Finished Loading: $url")
                binding.progressBar.isVisible = false
                url?.let {
                    if (it.contains("transaction_status=settlement")) {
                        finish()
                    }
                }
            }
        }
    }
}