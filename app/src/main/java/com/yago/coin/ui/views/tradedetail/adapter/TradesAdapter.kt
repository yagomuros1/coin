package com.yago.coin.ui.views.tradedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yago.coin.R
import com.yago.coin.domain.customdata.TradeInEur
import com.yago.coin.ui.views.tradedetail.adapter.viewholders.BaseTradeViewHolder
import com.yago.coin.ui.views.tradedetail.adapter.viewholders.HeaderViewHolder
import com.yago.coin.ui.views.tradedetail.adapter.viewholders.TradesViewHolder

class TradesAdapter : ListAdapter<TradeInEur, BaseTradeViewHolder>(

    object : DiffUtil.ItemCallback<TradeInEur>() {
        override fun areItemsTheSame(oldItem: TradeInEur, newItem: TradeInEur): Boolean = oldItem.sku == newItem.sku
        override fun areContentsTheSame(oldItem: TradeInEur, newItem: TradeInEur): Boolean = oldItem == newItem
    }) {

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM = 1
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> {
            ITEM_HEADER
        }
        else -> {
            ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTradeViewHolder {
        val baseViewHolder: BaseTradeViewHolder

        when (viewType) {
            ITEM_HEADER -> {
                baseViewHolder = HeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.trade_heder_item,
                        parent,
                        false
                    )
                )
            }

            else -> {
                baseViewHolder = TradesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.trade_detail_list_item,
                        parent,
                        false
                    )
                )
            }

        }
        return baseViewHolder
    }

    override fun onBindViewHolder(holder: BaseTradeViewHolder, position: Int) {
        val trade = getItem(position)
        holder.apply {
            bind(trade)
            itemView.tag = trade
        }
    }

}