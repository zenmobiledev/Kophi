package com.mobbelldev.kophi.presentation.ui.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.FragmentTransactionBinding
import com.mobbelldev.kophi.presentation.ui.coffee.payment.PaymentActivity
import com.mobbelldev.kophi.presentation.ui.transaction.adapter.ItemTransactionAdapter
import com.mobbelldev.kophi.presentation.ui.transaction.adapter.OnItemClickListener
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
        ItemTransactionAdapter(this)
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
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            val userId = transactionViewModel.getUserId()
            val token = transactionViewModel.getToken()
            binding.swipeRefresh.setOnRefreshListener {
                transactionViewModel.getOrders(
                    token = token,
                    userId = userId
                )
            }
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    transactionViewModel.isLoading.collect {
                        binding.progressBar.isVisible = it
                    }
                }

                launch {
                    transactionViewModel.errorMessage.filterNotNull().collect {
                        Toast.makeText(view?.context, "Error: $it", Toast.LENGTH_LONG).show()
                    }
                }

                launch {
                    transactionViewModel.orders.collect {
                        if (it == null) {
                            binding.shimmer.isVisible = true
                            return@collect
                        }

                        if (it.data.isNotEmpty()) {
                            binding.rvCoffees.isVisible = true
                            binding.ivTransactionCart.isVisible = false
                            binding.textNoTransaction.isVisible = false
                            transactionAdapter.submitList(it.data)
                        } else {
                            binding.rvCoffees.isVisible = false
                            binding.ivTransactionCart.isVisible = true
                            binding.textNoTransaction.isVisible = true
                        }

                        binding.swipeRefresh.isRefreshing = false
                        binding.shimmer.isVisible = false
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvCoffees.adapter = transactionAdapter
            rvCoffees.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    swipeRefresh.isEnabled = !rvCoffees.canScrollVertically(-1)
                }
            })
        }
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
        transactionAdapter.isClicked = true
    }

    override fun onCancelClick(transactionId: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.text_cancel_order))
            .setMessage(resources.getString(R.string.text_are_you_sure_cancel_order))
            .setPositiveButton(resources.getString(R.string.text_yes)) { _, _ ->
                lifecycleScope.launch {
                    val userId = transactionViewModel.getUserId()
                    val token = transactionViewModel.getToken()
                    transactionViewModel.cancelOrder(
                        userId = userId,
                        token = token,
                        transactionId = transactionId
                    )
                }
                transactionAdapter.isClicked = true
            }
            .setNegativeButton(resources.getString(R.string.text_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

    companion object {
        const val SNAP_URL_TOKEN_ID = "snap_url_token_id"
    }
}