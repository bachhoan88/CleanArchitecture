package com.example.cleanarchitecture.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.binding.FragmentDataBindingComponent
import com.example.cleanarchitecture.databinding.FragmentMainBinding
import com.example.cleanarchitecture.extension.showSoftKeyboard
import com.example.cleanarchitecture.util.autoCleared

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

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

        viewDataBinding.run {
            search.setOnClickListener {
                showSoftKeyboard(activity?.currentFocus?.windowToken, false)
                viewModel?.searchRepo()
            }
        }

        subscribeUI()
    }

    private fun subscribeUI() {
        val adapter = MainAdapter(bindingComponent) { item ->
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToTutorialFragment())
        }
        this.mainAdapter = adapter

        viewDataBinding.listRepo.adapter = mainAdapter

        viewModel.data.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.loading.observe(this, Observer { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        })
    }
}
