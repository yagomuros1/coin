package com.yago.coin.ui.views.tradedetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yago.coin.R
import com.yago.coin.databinding.ActivityTradeDetailBinding
import com.yago.coin.domain.customdata.TradeInEur
import com.yago.coin.ui.views.shared.base.BindingActivity
import com.yago.coin.ui.views.tradedetail.adapter.TradesAdapter
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.toast
import javax.inject.Inject

class TradeDetailActivity : BindingActivity<ActivityTradeDetailBinding>(), HasSupportFragmentInjector {

    companion object {
        private const val SKU_ID_PARAMETER = "skuId"
    }

    fun start(activity: AppCompatActivity?, skuSelected: String) {
        val intent = Intent(activity, TradeDetailActivity::class.java)
        intent.putExtra(SKU_ID_PARAMETER, skuSelected)
        activity?.startActivity(intent)
    }

    private val skuSelected: String? by lazy { intent.getStringExtra(SKU_ID_PARAMETER) }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var tradesAdapter: TradesAdapter

    private lateinit var tradeDetailViewModel: TradeDetailViewModel

    override fun createDataBinding(): ActivityTradeDetailBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_trade_detail)

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        tradeDetailViewModel = ViewModelProvider(this, viewModelFactory)[TradeDetailViewModel::class.java]

        tradesAdapter = TradesAdapter(this)

        tradesAdapter.listener = object : TradesAdapter.Listener {
            override fun onclick(trade: TradeInEur) {
                toast("clicked!")
            }
        }

        binding.tradesRecycler.layoutManager = LinearLayoutManager(this)
        binding.tradesRecycler.adapter = tradesAdapter

        initListeners()

        initializeViewObservers()

        supportFragmentManager.executePendingTransactions()
    }

    override fun onResume() {
        super.onResume()
        tradeDetailViewModel.onResumeMainScreen(skuSelected)
    }


    private fun initListeners() {

    }

    private fun initializeViewObservers() {

        tradeDetailViewModel.transactions.observe(this, { list ->
            list.sumOf { it.eurAmount.toDouble() }.let {
                binding.total.text = it.toString()
            }
            tradesAdapter.submitList(list)
        })

        tradeDetailViewModel.rates.observe(this, {
            Log.d("", "")
        })

    }

}