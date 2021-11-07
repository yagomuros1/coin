package com.yago.coin.ui.views.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yago.coin.data.db.entity.TransactionSkuData
import com.yago.coin.databinding.TradeListItemBinding

class SkusViewHolder(private val binding: TradeListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onClick: View.OnClickListener, item: TransactionSkuData) {
        binding.apply {
            this.clickListener = onClick
            this.sku.text = item.sku
            this.count.text = item.count.toString()
            executePendingBindings()
        }
    }

}