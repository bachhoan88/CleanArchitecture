package com.example.cleanarchitecture.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.binding.FragmentDataBindingComponent
import com.example.cleanarchitecture.databinding.FragmentMainBinding
import com.example.cleanarchitecture.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModels()

    private var mainAdapter by autoCleared<MainAdapter>()

    private var bindingComponent = FragmentDataBindingComponent(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainAdapter(bindingComponent) { item ->
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToContributorFragment(item))
        }
        this.mainAdapter = adapter

        with(viewDataBinding) {
            listRepo.adapter = mainAdapter
        }
    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            mainAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}
