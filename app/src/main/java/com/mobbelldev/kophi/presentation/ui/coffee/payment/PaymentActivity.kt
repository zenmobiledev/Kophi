package com.mobbelldev.kophi.presentation.ui.coffee.payment

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobbelldev.kophi.BuildConfig
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityPaymentBinding
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.CheckoutActivity
import com.mobbelldev.kophi.presentation.ui.main.MainActivity
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.webView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webView = binding.webView

        // Konfigurasi WebView
        webView.settings.apply {
            javaScriptEnabled = true // Aktifkan JavaScript jika diperlukan
            domStorageEnabled = true // Aktifkan penyimpanan DOM jika dibutuhkan
            cacheMode =
                WebSettings.LOAD_NO_CACHE // Hindari cache agar selalu memuat halaman terbaru
            allowContentAccess = true
            allowFileAccess = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        // WebView Client untuk menangani navigasi dalam aplikasi
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                println("FINISH: $url")
            }
        }

        // WebChromeClient untuk mendukung fitur tambahan seperti dialog JavaScript
        webView.webChromeClient = WebChromeClient()

        // Load URL dari DataStore
        val snapUrl = intent.getStringExtra(CheckoutActivity.EXTRA_URL_SNAP)
        val snapUrlTokenId = intent.getStringExtra(TransactionFragment.SNAP_URL_TOKEN_ID)
        when {
            snapUrl?.isNotEmpty() == true -> webView.loadUrl(snapUrl)
            snapUrlTokenId?.isNotEmpty() == true -> webView.loadUrl("${BuildConfig.SNAP_URL}$snapUrlTokenId")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_transaction)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}