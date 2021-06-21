package com.demo.presentation.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.demo.R
import com.demo.data.utils.Constants
import com.demo.data.utils.ResultState
import com.demo.databinding.FragmentMarketListBinding
import com.demo.presentation.extension.gone
import com.demo.presentation.extension.show
import com.demo.presentation.ui.adapters.MarketListAdapter
import com.demo.presentation.ui.viewmodels.MarketListViewModel
import com.demo.presentation.ui.viewmodels.MarketViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MarketListFragment : Fragment() {

    @Inject
    lateinit var factory: MarketViewModelFactory

    private val viewModel: MarketListViewModel by lazy {
        ViewModelProvider(requireActivity(), factory)
            .get(MarketListViewModel::class.java)
    }

    private lateinit var marketListAdapter: MarketListAdapter
    private lateinit var binding: FragmentMarketListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (::binding.isInitialized) {
            bindUpData()
        }
    }

    private fun bindUpData() {
        viewModel.apply {
            binding.progress.show()
            fetchMarketListFromRemote()
            getMarketListLiveData().observe(viewLifecycleOwner, {
                binding.progress.gone()
                when (it) {
                    is ResultState.Success -> {
                        if (it.data.isNotEmpty()) {
                            if (!::marketListAdapter.isInitialized) {
                                marketListAdapter = MarketListAdapter()
                            }
                            binding.rvList.apply {
                                adapter = marketListAdapter
                                addItemDecoration(
                                    DividerItemDecoration(
                                        activity,
                                        DividerItemDecoration.VERTICAL
                                    )
                                )
                                marketListAdapter.submitList(it.data)
                            }

                            binding.rvList.show()
                            binding.tvNoDataFound.gone()

                            marketListAdapter.setOnItemClickListener {
                                findNavController().navigate(
                                    R.id.action_listFragment_to_detailFragment,
                                    Bundle().apply {
                                        putParcelable(Constants.MODEL_DATA, it)
                                    })
                            }
                        } else {
                            binding.rvList.gone()
                            binding.tvNoDataFound.show()
                        }
                    }

                    is ResultState.Error -> {
                        if (Constants.NO_INTERNET_CONNECTION.equals(it.errorMessage, false) ||
                            Constants.TimeOut.equals(it.errorMessage, false)
                        ) {
                            showSnackBar()
                        } else {
                            Toast.makeText(activity, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun showSnackBar() {
        Snackbar.make(binding.root, R.string.text_no_internet, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.text_retry) {
                binding.progress.show()
                viewModel.fetchMarketListFromRemote()
            }
            .show()
    }
}