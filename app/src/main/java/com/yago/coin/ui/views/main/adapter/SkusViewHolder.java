package com.yago.coin.ui.views.main.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.yago.coin.R;
import com.yago.coin.data.db.entity.TransactionSkuData;
import com.yago.coin.databinding.TradeListItemBinding;

public final class SkusViewHolder extends RecyclerView.ViewHolder {

    private final TradeListItemBinding binding;

    public final void bind(View.OnClickListener onClick, TransactionSkuData item) {
        binding.setClickListener(onClick);
        binding.sku.setText(item.getSku());
        binding.count.setText(binding.count.getContext().getString(R.string.transactions, item.getCount()));
    }

    public SkusViewHolder(TradeListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

