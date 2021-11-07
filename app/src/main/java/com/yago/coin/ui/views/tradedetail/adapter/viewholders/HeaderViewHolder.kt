package com.yago.coin.ui.views.tradedetail.adapter.viewholders

import com.yago.coin.R
import com.yago.coin.databinding.TradeHederItemBinding
import com.yago.coin.domain.customdata.TradeInEur

class HeaderViewHolder(private val binding: TradeHederItemBinding) : BaseTradeViewHolder(binding.root) {

    override fun bind(item: TradeInEur) {
        binding.apply {
            this.total.text = this.total.context.getString(R.string.total_transactions, String.format("%.2f", item.amount))
            executePendingBindings()
        }
    }

}