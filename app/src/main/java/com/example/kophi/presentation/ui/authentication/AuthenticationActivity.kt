package com.example.kophi.presentation.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kophi.R
import com.example.kophi.databinding.ActivityAuthenticationBinding
import com.example.kophi.presentation.ui.main.MainActivity

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSignInWithGoogle.setOnClickListener {
//            Toast.makeText(this, "Navigate to Main Page", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
//            Toast.makeText(this, "Navigate to Main Page", Toast.LENGTH_SHORT).show()
        }
    }
}