package com.example.kophi.presentation.ui.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.example.kophi.R
import com.example.kophi.data.local.preference.PreferenceParameter
import com.example.kophi.databinding.ActivityAuthenticationBinding
import com.example.kophi.presentation.ui.main.MainActivity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var credentialManager: CredentialManager

    private lateinit var preferences: SharedPreferences

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            with(binding) {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                btnRegister.isEnabled =
                    name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

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
        credentialManager = CredentialManager.create(baseContext)

        auth = Firebase.auth

        preferences =
            getSharedPreferences(PreferenceParameter.MY_PREF, MODE_PRIVATE)
        val isLogin: Boolean = preferences.getBoolean(PreferenceParameter.IS_AUTHENTICATION, true)
        if (!isLogin) {
            finish()
            navigateToCoffeePage()
        }

        authenticateUser()
    }

    private fun authenticateUser() {
        // SIGN IN
        binding.btnSignInWithGoogle.setOnClickListener {
            val credentialManager =
                CredentialManager.create(this) //import from androidx.CredentialManager

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(getString(R.string.default_web_client_id))
                .build()

            val request = GetCredentialRequest.Builder() //import from androidx.CredentialManager
                .addCredentialOption(googleIdOption)
                .build()

            lifecycleScope.launch {
                try {
                    val result: GetCredentialResponse = credentialManager.getCredential(
                        //import from androidx.CredentialManager
                        request = request,
                        context = this@AuthenticationActivity,
                    )
                    handleSignIn(result)
                } catch (e: GetCredentialException) { //import from androidx.CredentialManager
                    Log.d("Error", e.message.toString())
                }
            }
        }

        // SIGN UP
        with(binding) {
            edtName.addTextChangedListener(textWatcher)
            edtEmail.addTextChangedListener(textWatcher)
            edtPassword.addTextChangedListener(textWatcher)

            // Navigate to Coffee Page
            btnRegister.setOnClickListener {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                if (btnRegister.isEnabled) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@AuthenticationActivity,
                                    "Signup success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateToCoffeePage().also {
                                    isLogin()
                                }
                            } else {
                                Toast.makeText(
                                    this@AuthenticationActivity,
                                    task.exception?.message.toString(),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                }
            }
        }

    }

    private fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and authenticate on your server.
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Log.d(TAG, "signInWithCredential:success")

                    navigateToCoffeePage().also {
                        isLogin()
                    }
                } else {
                    // If sign in fails, display a message to the user
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
    // [END auth_with_google]

    private fun isLogin() {
        preferences.edit()
            .putBoolean(PreferenceParameter.IS_AUTHENTICATION, false)
            .apply()
    }

    private fun navigateToCoffeePage() {
        finish()
        startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
    }
}