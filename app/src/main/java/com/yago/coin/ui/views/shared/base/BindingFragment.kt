package com.yago.coin.ui.views.shared.base

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.yago.coin.R
import com.yago.coin.data.utils.Status
import com.yago.coin.domain.injector.Injectable
import com.yago.coin.domain.livedata.autoCleared
import com.yago.coin.ui.binding.CoinDataBindingComponent
import javax.inject.Inject

abstract class BindingFragment<T : ViewDataBinding> : Fragment(), Injectable {

    var binding by autoCleared<T>()

    @Inject
    lateinit var dataBindingComponent: CoinDataBindingComponent

    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = createDataBinding(inflater, container)

        binding = dataBinding
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        // Animation Watchdog - Make sure we don't wait longer than a second for the Glide image
        handler.postDelayed(1000) {
            startPostponedEnterTransition()
        }
        postponeEnterTransition()

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onConfigurationChanged(resources.configuration)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            updateLayoutToLandscape(newConfig)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            updateLayoutPortrait(newConfig)
        }
    }

    abstract fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): T

    open fun updateLayoutToLandscape(newConfig: Configuration) = Unit
    open fun updateLayoutPortrait(newConfig: Configuration) = Unit

}
