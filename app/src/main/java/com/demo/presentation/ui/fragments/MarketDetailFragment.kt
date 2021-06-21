package com.demo.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.data.db.entities.MarketModel
import com.demo.data.utils.Constants
import com.demo.databinding.FragmentMarketDetailBinding
import com.demo.presentation.extension.setModelData
import com.demo.presentation.ui.viewmodels.MarketViewModelFactory
import kotlinx.android.synthetic.main.fragment_market_detail.*
import javax.inject.Inject

class MarketDetailFragment : Fragment() {

    @Inject
    lateinit var factory: MarketViewModelFactory
    private lateinit var binding: FragmentMarketDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindDataToUI()
    }

    private fun bindDataToUI() {
        if (::binding.isInitialized) {
            arguments?.apply {
                getParcelable<MarketModel>(Constants.MODEL_DATA)?.apply {
                    tvExchange.setModelData("Exchange Id: ", exchangeId.toString())
                    tvSymbols.setModelData("Symbol: ", symbol)
                    tvPriceUncovered.setModelData("Price Uncovered: ", priceUnconverted.toString())
                    tvPrices.setModelData("Price: ", price.toString())
                    tvChange.setModelData("Change 24h: ", change24h.toString())
                    tvSpread.setModelData("Spread: ", spread.toString())
                    tvVolume24h.setModelData("Volume 24h: ", volume24h.toString())
                    tvStatus.setModelData("Status: ", status)
                    tvTimeStamp.setModelData("TimeStamp: ", time.toString())
                }
            }
        }
    }

}