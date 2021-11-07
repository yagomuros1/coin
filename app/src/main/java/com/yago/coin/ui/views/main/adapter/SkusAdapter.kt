package com.yago.coin.ui.views.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yago.coin.R
import com.yago.coin.data.db.entity.TransactionSkuData

class SkusAdapter(private val context: Context?) : ListAdapter<TransactionSkuData, SkusViewHolder>(

    object : DiffUtil.ItemCallback<TransactionSkuData>() {
        override fun areItemsTheSame(oldItem: TransactionSkuData, newItem: TransactionSkuData): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TransactionSkuData, newItem: TransactionSkuData): Boolean = oldItem == newItem

    }) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkusViewHolder {
        return SkusViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.trade_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SkusViewHolder, position: Int) {
        val trade = getItem(position)
        holder.apply {
            bind(createOnClickListener(trade), trade)
            itemView.tag = trade
        }
    }

    private fun createOnClickListener(sku: TransactionSkuData): View.OnClickListener {
        return View.OnClickListener {
            context.let {
                listener?.onclick(sku)
            }
        }
    }

    interface Listener {
        fun onclick(sku: TransactionSkuData)
    }

}