package com.mobbelldev.kophi.presentation.ui.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mobbelldev.kophi.databinding.FragmentTransactionBinding
import com.mobbelldev.kophi.presentation.ui.coffee.payment.PaymentActivity
import com.mobbelldev.kophi.presentation.ui.transaction.adapter.OnItemClickListener
import com.mobbelldev.kophi.presentation.ui.transaction.adapter.OuterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentTransactionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val transactionViewModel: TransactionViewModel by viewModels()

    private val transactionAdapter by lazy {
        OuterAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    transactionViewModel.isLoading.collect {
                        binding.progressBar.isVisible = it
                        binding.swipeRefresh.isRefreshing = it
                    }
                }

                launch {
                    transactionViewModel.errorMessage.filterNotNull().collect {
                        Toast.makeText(view?.context, "Error: $it", Toast.LENGTH_LONG).show()
                    }
                }

                launch {
                    transactionViewModel.orders.collect {
                        if (it != null) {
                            transactionAdapter.submitList(it.data)
                        } else {
                            binding.ivTransactionCart.isVisible = true
                            binding.textNoTransaction.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCoffees.adapter = transactionAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickPay(orderTokenId: String) {
        val intent = Intent(requireContext(), PaymentActivity::class.java).apply {
            putExtra(SNAP_URL_TOKEN_ID, orderTokenId)
        }
        startActivity(intent)
    }

    companion object {
        const val SNAP_URL_TOKEN_ID = "snap_url_token_id"
    }
}