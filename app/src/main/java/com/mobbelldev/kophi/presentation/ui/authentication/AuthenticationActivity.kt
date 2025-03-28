package com.mobbelldev.kophi.presentation.ui.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobbelldev.kophi.BuildConfig
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityAuthenticationBinding
import com.mobbelldev.kophi.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var credentialManager: CredentialManager

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

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

        setupObserver()
        authenticateUser()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    authenticationViewModel.isLoading.collect { isLoading ->
                        binding.progressBar.isVisible = isLoading
                    }
                }

                launch {
                    authenticationViewModel.errorMessage.collect { message ->
                        Toast.makeText(
                            this@AuthenticationActivity,
                            "Error: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                launch {
                    authenticationViewModel.dataUser.collect {
                        if (it != null) {
                            navigateToCoffeePage()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToCoffeePage() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    // Handle the result of the credential request.
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
                    auth.currentUser?.getIdToken(true)?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val tokenFirebase = it.result?.token
                            lifecycleScope.launch {
                                authenticationViewModel.continueWithGoogle(
                                    rememberMe = true,
                                    token = tokenFirebase.toString()
                                )
                            }

                        }
                    }

                    lifecycleScope.launch {
                        authenticationViewModel.setEmail(auth.currentUser?.email.toString())
                    }
                    binding.btnSignInWithGoogle.isEnabled = false
                    Log.d(TAG, "signInWithCredential:success")
                } else {
                    // If sign in fails, display a message to the user
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    binding.btnSignInWithGoogle.isEnabled = true
                }
            }
    }
    // [END auth_with_google]

    private fun authenticateUser() {
        // SIGN IN
        binding.btnSignInWithGoogle.setOnClickListener {
            val credentialManager =
                CredentialManager.create(this)

            val token = BuildConfig.DEFAULT_WEB_CLIENT_ID
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(token)
                .build()

            val request =
                GetCredentialRequest.Builder()
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
                } catch (e: GetCredentialException) {
                    Log.d("Error", e.message.toString())
                }
            }
        }
    }
}