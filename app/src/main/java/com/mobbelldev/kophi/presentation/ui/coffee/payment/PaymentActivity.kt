package com.mobbelldev.kophi.presentation.ui.coffee.payment

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityPaymentBinding
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.CheckoutActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    private val paymentViewModel: PaymentViewModel by viewModels()
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

        }

        // WebChromeClient untuk mendukung fitur tambahan seperti dialog JavaScript
        webView.webChromeClient = WebChromeClient()

        // Load URL dari DataStore
        val snapUrl = intent.getStringExtra(CheckoutActivity.EXTRA_URL_SNAP)
        if (snapUrl != "") {
            webView.loadUrl(snapUrl.toString())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, CheckoutActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}