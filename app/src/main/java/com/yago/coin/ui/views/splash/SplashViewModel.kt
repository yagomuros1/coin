package com.yago.coin.ui.views.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.utils.Resource
import com.yago.coin.data.utils.Status
import com.yago.coin.domain.interactor.MainInteractor
import javax.inject.Inject


class SplashViewModel @Inject constructor(private val mainInteractor: MainInteractor) : ViewModel() {

    companion object {
        const val NO_ERROR_CODE = -1
    }

    val rates: LiveData<Resource<List<Rate>>> = Transformations
        .switchMap(mainInteractor.rates) { contexts ->
            val result = MutableLiveData<Resource<List<Rate>>>()

            when (contexts.status) {
                Status.ERROR -> {
                    val errorCode = contexts.code ?: NO_ERROR_CODE
                    val errorMessage = "Error"
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
                    mainInteractor.getTrades()
                    result.postValue(contexts)
                    result
                }
            }
        }

    val transactions: LiveData<Resource<List<Trade>>> = Transformations
        .switchMap(mainInteractor.transactions) { contexts ->
            val result = MutableLiveData<Resource<List<Trade>>>()

            when (contexts.status) {
                Status.ERROR -> {
                    val errorCode = contexts.code ?: NO_ERROR_CODE
                    val errorMessage = "Error"
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
                    result.postValue(contexts)
                    result
                }
            }
        }

    fun onCreateSplashScreen() {
        mainInteractor.getRates()
    }

}