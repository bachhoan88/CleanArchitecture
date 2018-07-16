package com.example.cleanarchitecture.ui.splash

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(), SplashNavigator {
    companion object {
        const val TAG = "SplashFragment"
        fun newInstance() = SplashFragment()

    }

    override val themeId: Int
        get() = R.style.AppTheme_NoActionBar

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override val viewModel: SplashViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigator = this
    }

}
