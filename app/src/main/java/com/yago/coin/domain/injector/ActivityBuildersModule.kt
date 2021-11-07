package com.yago.coin.domain.injector

import com.yago.coin.ui.views.main.MainActivity
import com.yago.coin.ui.views.tradedetail.TradeDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeTradeDetailActivity(): TradeDetailActivity

}