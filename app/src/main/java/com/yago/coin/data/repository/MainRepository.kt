package com.yago.coin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.yago.coin.R
import com.yago.coin.app.AppExecutors
import com.yago.coin.data.api.CoinBackendApi
import com.yago.coin.data.api.ApiResponse
import com.yago.coin.data.api.response.DataResponse
import com.yago.coin.data.api.response.UserTokenResponse
import com.yago.coin.data.db.CoinDb
import com.yago.coin.data.db.dao.DataDao
import com.yago.coin.data.db.entity.Data
import com.yago.coin.data.repository.assembler.DataAssembler
import com.yago.coin.data.utils.DbAndApiNetworkBoundResource
import com.yago.coin.data.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val app: Application,
    private val appExecutors: AppExecutors,
    private val dataDao: DataDao,
    private val coinBackendApi: CoinBackendApi,
    private val coinDb: CoinDb
) : ApiRepository() {

    fun getData(): LiveData<Resource<Data>> {
        return object : DbAndApiNetworkBoundResource<Data, DataResponse>(appExecutors) {

            override fun processTryToRefreshResponse(userTokenResponse: ApiResponse<UserTokenResponse>?) =
                processRefreshToken()

            override fun refreshTokenCall() = tryToRefreshToken()

            override fun shouldFetch(data: Data?): Boolean {
                return true
            }

            override fun saveCallResult(item: DataResponse) {
                DataAssembler.assembleData(coinDb, item, app)
            }

            override fun loadFromDb(): LiveData<Data> {

                val dataLD = MediatorLiveData<Data>()

                appExecutors.diskIO().execute {

                    appExecutors.mainThread().execute {
                        dataLD.value = Data("2", app.getString(R.string.app_name))
                    }
                }
                return dataLD
            }

            override fun createCall() = coinBackendApi.getData()

        }.asLiveData()
    }


}




