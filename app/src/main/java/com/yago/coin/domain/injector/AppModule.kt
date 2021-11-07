package com.yago.coin.domain.injector

import android.app.Application
import androidx.room.Room
import com.yago.coin.data.api.CoinBackendApi
import com.yago.coin.data.api.RestAdapter
import com.yago.coin.data.db.CoinDb
import com.yago.coin.data.db.dao.RateDao
import com.yago.coin.ui.binding.CoinDataBindingComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDataBindingComponent(application: Application): CoinDataBindingComponent = CoinDataBindingComponent(application)


    @Singleton
    @Provides
    fun provideCoinBackendApi(restAdapter: RestAdapter): CoinBackendApi = restAdapter.getCoinBackendApi()


    @Singleton
    @Provides
    fun provideDb(app: Application): CoinDb {
        return Room
            .databaseBuilder(app, CoinDb::class.java, "database")
            //TODO disable line after project initialization
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideRateDao(db: CoinDb): RateDao = db.rateDao()

}