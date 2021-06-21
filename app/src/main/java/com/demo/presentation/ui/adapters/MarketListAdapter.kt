package com.demo.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.db.entities.MarketModel
import com.demo.databinding.AdapterMarketListBinding
import com.demo.presentation.extension.setTextWithVisibility

class MarketListAdapter : ListAdapter<MarketModel,
        MarketListAdapter.MarketViewHolder>(DiffCallback()) {


    private class DiffCallback : DiffUtil.ItemCallback<MarketModel>() {

        override fun areItemsTheSame(
            oldItem: MarketModel,
            newItem: MarketModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MarketModel,
            newItem: MarketModel
        ): Boolean {
            return oldItem.exchangeId == newItem.exchangeId
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketViewHolder {
        return MarketViewHolder(
            AdapterMarketListBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MarketViewHolder(private val binding: AdapterMarketListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarketModel) {
            binding.apply {
                item.apply {
                    tvExchangeId.setTextWithVisibility(exchangeId)
                    tvSymbol.setTextWithVisibility(symbol)
                    tvPrice.text = price.toString()
                    tvVolume.text = volume24h.toString()
                }

                root.setOnClickListener {
                    onClickOnItem?.let { it(item) }
                }
            }
        }
    }

    private var onClickOnItem: ((MarketModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (MarketModel) -> Unit) {
        onClickOnItem = listener
    }
}