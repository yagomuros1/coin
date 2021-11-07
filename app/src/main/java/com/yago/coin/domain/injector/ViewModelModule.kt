package com.yago.coin.domain.injector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yago.coin.ui.views.main.MainViewModel
import com.yago.coin.ui.views.tradedetail.TradeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: CoinViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TradeDetailViewModel::class)
    abstract fun bindTradeDetailViewModel(tradeDetailViewModel: TradeDetailViewModel): ViewModel

}