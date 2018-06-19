package com.example.cleanarchitecture.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.databinding.MainFragmentBinding

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>(), MainNavigator {
    companion object {
        fun newInstance() = MainFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.main_fragment

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigator = this
    }
}
