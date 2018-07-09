package com.example.cleanarchitecture.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.binding.FragmentDataBindingComponent
import com.example.cleanarchitecture.databinding.FragmentMainBinding
import com.example.cleanarchitecture.util.autoCleared

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(), MainNavigator {
    companion object {
        fun newInstance() = MainFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_main

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    private var mainAdapter by autoCleared<MainAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigator = this

        val adapter = MainAdapter(bindingComponent, { repoItem -> })

        viewDataBinding.listRepo.layoutManager = LinearLayoutManager(activity)
        this.mainAdapter = adapter
        viewDataBinding.listRepo.adapter = mainAdapter

        viewModel.data.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun showLoading() {
        super.showLoading()
        hideKeyboard()
    }
}
