package com.yago.coin.ui.views.tradedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yago.coin.R
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.domain.customdata.TradeInEur

class TradesAdapter(private val context: Context?) : ListAdapter<TradeInEur, TradesViewHolder>(

    object : DiffUtil.ItemCallback<TradeInEur>() {
        override fun areItemsTheSame(oldItem: TradeInEur, newItem: TradeInEur): Boolean = false
        override fun areContentsTheSame(oldItem: TradeInEur, newItem: TradeInEur): Boolean = false

    }) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradesViewHolder {
        return TradesViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.trade_detail_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TradesViewHolder, position: Int) {
        val trade = getItem(position)
        holder.apply {
            bind(createOnClickListener(trade), trade)
            itemView.tag = trade
        }
    }

    private fun createOnClickListener(trade: TradeInEur): View.OnClickListener {
        return View.OnClickListener {
            context.let {
                listener?.onclick(trade)
            }
        }
    }

    interface Listener {
        fun onclick(trade: TradeInEur)
    }

}