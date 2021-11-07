package com.yago.coin.ui.views.tradedetail.adapter.viewholders

import android.view.View
import com.yago.coin.databinding.TradeDetailListItemBinding
import com.yago.coin.domain.customdata.TradeInEur

class TradesViewHolder(private val binding: TradeDetailListItemBinding) : BaseTradeViewHolder(binding.root) {

    override fun bind(onClick: View.OnClickListener, item: TradeInEur) {
        binding.apply {
            this.clickListener = onClick
            this.trade.text = String.format("%.2f", item.amount)
            this.tradeCurrency.text = item.currency
            this.eurAmount.text = String.format("%.2f", item.eurAmount)
            executePendingBindings()
        }
    }

}