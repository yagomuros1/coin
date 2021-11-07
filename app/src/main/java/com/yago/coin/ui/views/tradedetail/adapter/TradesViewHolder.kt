package com.yago.coin.ui.views.tradedetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yago.coin.databinding.TradeDetailListItemBinding
import com.yago.coin.domain.customdata.TradeInEur

class TradesViewHolder(private val binding: TradeDetailListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onClick: View.OnClickListener, item: TradeInEur) {
        binding.apply {
            this.clickListener = onClick
            this.trade.text = item.amount
            this.tradeCurrency.text = item.currency
            this.eurAmount.text = item.eurAmount
            executePendingBindings()
        }
    }

}