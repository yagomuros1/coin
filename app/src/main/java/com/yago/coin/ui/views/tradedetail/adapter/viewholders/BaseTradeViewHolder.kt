package com.yago.coin.ui.views.tradedetail.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yago.coin.domain.customdata.TradeInEur

abstract class BaseTradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: TradeInEur)

}