package com.yago.coin.ui.views.tradedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.utils.Resource
import com.yago.coin.data.utils.Status
import com.yago.coin.domain.customdata.TradeInEur
import com.yago.coin.domain.interactor.MainInteractor
import com.yago.coin.ui.views.main.MainViewModel
import javax.inject.Inject

class TradeDetailViewModel @Inject constructor(private val mainInteractor: MainInteractor) : ViewModel() {

    private var rateList: List<Rate>? = emptyList()
    private var sku: String? = null

    val rates: LiveData<Resource<List<Rate>>> = Transformations
        .switchMap(mainInteractor.rates) { rates ->
            val result = MutableLiveData<Resource<List<Rate>>>()

            when (rates.status) {
                Status.ERROR -> {
                    val errorCode = rates.code ?: MainViewModel.NO_ERROR_CODE
                    val errorMessage = "ErrorUtils.getMessageFromError(errorCode, app)"
                    result.postValue(Resource.error(errorCode, errorMessage, null))
                    result
                }
                Status.LOADING -> {
                    result.postValue(Resource.loading(null))
                    result
                }
                Status.FASTLOAD -> {
                    result.postValue(Resource.fastload(null))
                    result
                }
                else -> {
                    rateList = rates.data
                    mainInteractor.getTradesBySku(sku)
                    result.postValue(rates)
                    result
                }
            }
        }

    val transactions: LiveData<List<TradeInEur>> = Transformations
        .switchMap(mainInteractor.transactionsBySku) { transactions ->
            val result = MutableLiveData<List<TradeInEur>>()
            result.postValue(convertToEuro(transactions))
            result
        }

    fun onResumeMainScreen(sku: String?) {
        this.sku = sku
        mainInteractor.getRates()
    }

    private fun convertToEuro(trades: List<Trade>): List<TradeInEur> {

        val er: MutableList<TradeInEur> = mutableListOf()

        trades.forEach {
            er.add(TradeInEur(it.sku, it.amount, getEurAmountFromCurrency(it.currency, it.amount).toString(), it.currency))
        }

        return er

    }

    private fun getEurAmountFromCurrency(currency: String?, amount: String?): Double? {

        if (currency == "EUR") {
            return amount?.toDouble()
        } else {

            val isDirectConversion = rateList?.find { it.from == currency && it.to == "EUR" } != null

            if (isDirectConversion) {

                rateList?.find { it.from == currency && it.to == "EUR" }?.rate?.toDouble()?.let {
                    return amount?.toDouble()?.times(it)
                }

            } else {

                rateList?.filter { it.to == currency }?.let { filtered ->
                    rateList?.find { it.from == currency && it.to == filtered.first().from }?.rate?.let { rateConversion ->
                        return getEurAmountFromCurrency(
                            filtered.first().from,
                            (amount?.toDouble()?.times(rateConversion.toDouble())).toString()
                        )
                    }
                }
            }
        }
        return 2.4
    }

}