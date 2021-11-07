package com.yago.coin.ui.views.tradedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.domain.customdata.TradeInEur
import com.yago.coin.domain.interactor.MainInteractor
import javax.inject.Inject

class TradeDetailViewModel @Inject constructor(private val mainInteractor: MainInteractor) : ViewModel() {

    companion object {
        const val EUR_CODE = "EUR"
    }

    private var rateList: List<Rate>? = emptyList()
    private var sku: String? = null

    val rates: LiveData<List<Rate>> = Transformations
        .switchMap(mainInteractor.dbRates) { rates ->
            rateList = rates
            mainInteractor.getTradesBySku(sku)
            val result = MutableLiveData<List<Rate>>()
            result.postValue(rates)
            result
        }

    val transactions: LiveData<List<TradeInEur>> = Transformations
        .switchMap(mainInteractor.transactionsBySku) { transactions ->
            val result = MutableLiveData<List<TradeInEur>>()
            addTotalValueAtFirstItem(convertToEuro(transactions))
            result.postValue(addTotalValueAtFirstItem(convertToEuro(transactions)))
            result
        }

    fun onCreateTradeScreen(sku: String?) {
        this.sku = sku
        mainInteractor.getSavedRates()
    }

    private fun convertToEuro(trades: List<Trade>): List<TradeInEur> {
        val listWithEur: MutableList<TradeInEur> = mutableListOf()
        trades.forEach {
            listWithEur.add(
                TradeInEur(
                    it.sku,
                    it.amount,
                    getEurAmountFromCurrency(it.currency, it.amount),
                    it.currency
                )
            )
        }
        return listWithEur
    }

    private fun addTotalValueAtFirstItem(list: List<TradeInEur>): List<TradeInEur> {
        val finalList: MutableList<TradeInEur> = mutableListOf()
        list.mapNotNull { it.eurAmount }.sumOf { it }.let {
            finalList.add(TradeInEur(null, it, it, EUR_CODE))
        }
        finalList.addAll(list)
        return finalList
    }

    private fun getEurAmountFromCurrency(currency: String?, amount: Double?): Double? {
        if (currency == EUR_CODE) {
            return amount
        } else {
            val isDirectConversion = rateList?.find { it.from == currency && it.to == EUR_CODE } != null
            if (isDirectConversion) {
                rateList?.find { it.from == currency && it.to == EUR_CODE }?.rate?.let {
                    return amount?.times(it)
                }
            } else {
                rateList?.filter { it.to == currency }?.let { filtered ->
                    rateList?.find { it.from == currency && it.to == filtered.first().from }?.rate?.let { rateConversion ->
                        return getEurAmountFromCurrency(
                            filtered.first().from,
                            (amount?.times(rateConversion))
                        )
                    }
                }
            }
        }
        return -1.0
    }

}