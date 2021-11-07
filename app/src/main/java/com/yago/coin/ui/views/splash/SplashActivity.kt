package com.yago.coin.ui.views.splash

import android.app.AlertDialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yago.coin.R
import com.yago.coin.data.utils.Status
import com.yago.coin.databinding.ActivitySplashBinding
import com.yago.coin.domain.utils.ConnectivityUtils
import com.yago.coin.ui.views.main.MainActivity
import com.yago.coin.ui.views.shared.base.BindingActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.toast
import javax.inject.Inject

class SplashActivity : BindingActivity<ActivitySplashBinding>(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var splashViewModel: SplashViewModel

    override fun createDataBinding(): ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]

        initializeViewObservers()

        ConnectivityUtils.checkNetWorkAndExecuteCallback(this, {
            splashViewModel.onCreateSplashScreen()
        }, {
            showNoNetworkConnection()
        })
    }

    private fun initializeViewObservers() {

        splashViewModel.rates.observe(this, { value ->
            if (value.status == Status.ERROR) {
                value.message?.let {
                    toast(value.message)
                }
            }
        })

        splashViewModel.transactions.observe(this, { value ->

            if (value.status == Status.SUCCESS) {
                MainActivity().start(this)
            } else if (value.status == Status.ERROR) {
                value.message?.let {
                    toast(value.message)
                }
            }
        })
    }

    private fun showNoNetworkConnection() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.no_connection_title))
        builder.setMessage(getString(R.string.no_connection_description))
        builder.setPositiveButton("OK") { _, _ ->
            finish()
        }
        builder.show()
    }
}