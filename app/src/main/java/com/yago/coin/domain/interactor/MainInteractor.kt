package com.yago.coin.domain.interactor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.repository.MainRepository
import com.yago.coin.data.utils.Resource
import com.yago.coin.data.db.entity.TransactionSkuData
import com.yago.coin.domain.livedata.AbsentLiveData
import javax.inject.Inject

class MainInteractor @Inject constructor(private val app: Application, private val mainRepository: MainRepository) {

    private val rateTrigger = MutableLiveData<Unit>()
    private val transactionTrigger = MutableLiveData<Unit>()
    private val distinctTransactionsTrigger = MutableLiveData<Unit>()
    private val skuTransactionsFilter = MutableLiveData<String?>()

    val rates: LiveData<Resource<List<Rate>>> = Transformations
        .switchMap(rateTrigger) { filter ->
            if (filter != null) {
                mainRepository.getRates()
            } else {
                AbsentLiveData.create()
            }
        }

    val transactions: LiveData<Resource<List<Trade>>> = Transformations
        .switchMap(transactionTrigger) { filter ->
            if (filter != null) {
                mainRepository.getTrades()
            } else {
                AbsentLiveData.create()
            }
        }

    val distinct: LiveData<List<TransactionSkuData>> = Transformations
        .switchMap(distinctTransactionsTrigger) { filter ->
            if (filter != null) {
                mainRepository.getDistinctTrades()
            } else {
                AbsentLiveData.create()
            }
        }

    val transactionsBySku: LiveData<List<Trade>> = Transformations
        .switchMap(skuTransactionsFilter) { sku ->
            if (sku != null) {
                mainRepository.getSkuTransactions(sku)
            } else {
                AbsentLiveData.create()
            }
        }

    fun getRates() {
        rateTrigger.value = Unit
    }

    fun getTrades() {
        transactionTrigger.value = Unit
    }

    fun getDistinctTrades() {
        distinctTransactionsTrigger.value = Unit
    }

    fun getTradesBySku(sku: String?) {
        skuTransactionsFilter.value = sku
    }

    fun getTransactionsInEuro(sku: String?) {



    }

}