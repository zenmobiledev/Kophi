package com.example.kophi.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kophi.R
import com.example.kophi.databinding.FragmentProfileBinding
import com.example.kophi.presentation.ui.authentication.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

        binding.cardSettingsDarkMode.setOnClickListener {
            // Handle dark mode settings
            Toast.makeText(context, "Activated Dark Mode", Toast.LENGTH_SHORT).show()
        }

        binding.cardSettingsLanguage.setOnClickListener {
            // Handle language settings
            Toast.makeText(context, "Language", Toast.LENGTH_SHORT).show()
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}