package com.yago.coin.ui.views.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yago.coin.R
import com.yago.coin.data.utils.Status
import com.yago.coin.databinding.ActivityMainBinding
import com.yago.coin.databinding.ActivitySplashBinding
import com.yago.coin.ui.views.main.MainActivity
import com.yago.coin.ui.views.main.MainViewModel
import com.yago.coin.ui.views.main.adapter.SkusAdapter
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

        splashViewModel.onResumeMainScreen()

    }

    private fun initializeViewObservers() {

        splashViewModel.rates.observe(this, { value ->
            if (value.status == Status.ERROR) {
                toast("Error!")
            }
        })

        splashViewModel.transactions.observe(this, { value ->

            if (value.status == Status.SUCCESS) {
                MainActivity().start(this)
            } else if (value.status == Status.ERROR) {
                toast("Error!")
            }

        })


    }
}