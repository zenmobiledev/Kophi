package com.mobbelldev.kophi.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.FragmentProfileBinding
import com.mobbelldev.kophi.presentation.ui.authentication.AuthenticationActivity
import com.mobbelldev.kophi.presentation.ui.profile.language.LanguageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = auth.currentUser
        binding.tvProfileName.text = resources.getString(R.string.hello_name, user?.displayName)

        setupObserver()
        setupDarkMode()
        setupLanguage()
        setupLogout()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    profileViewModel.getDarkMode().collect {
                        binding.materialSwitch.isChecked = it
                    }
                }
            }
        }
    }

    private fun setupLogout() {
        binding.cardSettingsLogout.setOnClickListener {
            // Handle logout settings
            auth.signOut()
            lifecycleScope.launch {
                profileViewModel.logout()
                val intent = Intent(requireContext(), AuthenticationActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    private fun setupLanguage() {
        binding.cardSettingsLanguage.setOnClickListener {
            // Handle language settings
            startActivity(Intent(requireContext(), LanguageActivity::class.java))
        }
    }

    private fun setupDarkMode() {
        binding.materialSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                profileViewModel.setDarkMode(isChecked)
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}