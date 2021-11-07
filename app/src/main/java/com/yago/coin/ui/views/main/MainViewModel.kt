package com.yago.coin.ui.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.db.entity.TransactionSkuData
import com.yago.coin.domain.interactor.MainInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainInteractor: MainInteractor) : ViewModel() {

    val distinct: LiveData<List<TransactionSkuData>> = Transformations
        .switchMap(mainInteractor.distinct) { contexts ->
            val result = MutableLiveData<List<TransactionSkuData>>()
            result.postValue(contexts)
            result
        }

    fun onResumeMainScreen() {
        mainInteractor.getDistinctTrades()
    }

}