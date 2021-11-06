package com.yago.coin.ui.views.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yago.coin.R
import com.yago.coin.databinding.ActivityMainBinding
import com.yago.coin.ui.views.shared.base.BindingActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
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

    override fun createDataBinding(): ActivityMainBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_main)

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        binding.lifecycleOwner = this

        initListeners()

        supportFragmentManager.executePendingTransactions()
    }


    private fun initListeners() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

}