package com.yago.coin.ui.binding

import android.content.Context
import androidx.databinding.DataBindingComponent

class CoinDataBindingComponent(context: Context) : DataBindingComponent {

    private val adapter = FragmentBindingAdapters(context)

    override fun getFragmentBindingAdapters() = adapter
}

