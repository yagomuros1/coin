package com.yago.coin.ui.views.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yago.coin.R
import com.yago.coin.data.utils.Status
import com.yago.coin.databinding.ActivityMainBinding
import com.yago.coin.data.db.entity.TransactionSkuData
import com.yago.coin.ui.views.main.adapter.SkusAdapter
import com.yago.coin.ui.views.shared.base.BindingActivity
import com.yago.coin.ui.views.tradedetail.TradeDetailActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BindingActivity<ActivityMainBinding>(), HasSupportFragmentInjector {

    fun start(activity: AppCompatActivity?) {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var skusAdapter: SkusAdapter

    override fun createDataBinding(): ActivityMainBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_main)

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        skusAdapter = SkusAdapter(this)

        skusAdapter.listener = object : SkusAdapter.Listener {
            override fun onclick(sku: TransactionSkuData) {
                TradeDetailActivity().start(this@MainActivity, sku.sku)
            }

        }

        initListeners()

        initializeViewObservers()

        binding.tradesRecycler.layoutManager = LinearLayoutManager(this)
        binding.tradesRecycler.adapter = skusAdapter

        mainViewModel.onResumeMainScreen()
    }

    private fun initListeners() {

    }

    private fun initializeViewObservers() {

        mainViewModel.rates.observe(this, { value ->

            if (value.status == Status.SUCCESS) {
                mainViewModel.onGetTransactions()
            } else if (value.status == Status.ERROR) {
                toast("Error!")
            }

        })

        mainViewModel.transactions.observe(this, { value ->

            if (value.status == Status.SUCCESS) {
                mainViewModel.getDistinctTrades()
            } else if (value.status == Status.ERROR) {
                toast("Error!")
            }

        })

        mainViewModel.distinct.observe(this, { value ->

            skusAdapter.submitList(value)

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

}