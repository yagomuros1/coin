package com.yago.coin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.yago.coin.app.AppExecutors
import com.yago.coin.data.api.ApiResponse
import com.yago.coin.data.api.CoinBackendApi
import com.yago.coin.data.api.response.RateResponse
import com.yago.coin.data.api.response.TransactionResponse
import com.yago.coin.data.api.response.UserTokenResponse
import com.yago.coin.data.db.CoinDb
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.db.entity.TransactionSkuData
import com.yago.coin.data.repository.assembler.DataAssembler
import com.yago.coin.data.utils.DbAndApiNetworkBoundResource
import com.yago.coin.data.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val app: Application,
    private val appExecutors: AppExecutors,
    private val coinBackendApi: CoinBackendApi,
    private val coinDb: CoinDb
) : ApiRepository() {

    fun getRates(): LiveData<Resource<List<Rate>>> {
        return object : DbAndApiNetworkBoundResource<List<Rate>, List<RateResponse>>(appExecutors) {

            override fun processTryToRefreshResponse(userTokenResponse: ApiResponse<UserTokenResponse>?) =
                processRefreshToken()

            override fun refreshTokenCall() = tryToRefreshToken()

            override fun shouldFetch(data: List<Rate>?) = true

            override fun saveCallResult(item: List<RateResponse>) {
                DataAssembler.assembleRates(coinDb, item, app)
            }

            override fun loadFromDb(): LiveData<List<Rate>> {

                val dataLD = MediatorLiveData<List<Rate>>()

                appExecutors.diskIO().execute {

                    val list = coinDb.rateDao().getAllData()

                    appExecutors.mainThread().execute {
                        dataLD.value = list
                    }
                }
                return dataLD
            }

            override fun createCall() = coinBackendApi.getRates()

        }.asLiveData()
    }


    fun getTrades(): LiveData<Resource<List<Trade>>> {
        return object : DbAndApiNetworkBoundResource<List<Trade>, List<TransactionResponse>>(appExecutors) {

            override fun processTryToRefreshResponse(userTokenResponse: ApiResponse<UserTokenResponse>?) =
                processRefreshToken()

            override fun refreshTokenCall() = tryToRefreshToken()

            override fun shouldFetch(data: List<Trade>?) = true

            override fun saveCallResult(item: List<TransactionResponse>) {
                DataAssembler.assembleTrades(coinDb, item, app)
            }

            override fun loadFromDb(): LiveData<List<Trade>> {

                val dataLD = MediatorLiveData<List<Trade>>()

                appExecutors.diskIO().execute {

                    val list = coinDb.tradeDao().getAllData()

                    appExecutors.mainThread().execute {
                        dataLD.value = list
                    }
                }
                return dataLD
            }

            override fun createCall() = coinBackendApi.getTrades()

        }.asLiveData()
    }

    fun getDistinctTrades(): LiveData<List<TransactionSkuData>> {

        val dataLD = MediatorLiveData<List<TransactionSkuData>>()

        appExecutors.diskIO().execute {

            val list = coinDb.tradeDao().getAllDistinctSkus()

            appExecutors.mainThread().execute {
                dataLD.value = list
            }
        }
        return dataLD

    }

    fun getSkuTransactions(sku: String): LiveData<List<Trade>> {

        val dataLD = MediatorLiveData<List<Trade>>()

        appExecutors.diskIO().execute {

            val list = coinDb.tradeDao().getTradesBySku(sku)

            appExecutors.mainThread().execute {
                dataLD.value = list
            }
        }
        return dataLD

    }

}




