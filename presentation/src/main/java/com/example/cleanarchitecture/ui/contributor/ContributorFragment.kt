package com.example.cleanarchitecture.ui.contributor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import androidx.lifecycle.observe
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.binding.FragmentDataBindingComponent
import com.example.cleanarchitecture.databinding.FragmentContributorBinding
import com.example.cleanarchitecture.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContributorFragment : BaseFragment<FragmentContributorBinding, ContributorViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModels<ContributorViewModel>()

    override val layoutId: Int = R.layout.fragment_contributor

    private val args by navArgs<ContributorFragmentArgs>()

    private var contributorAdapter by autoCleared<ContributorAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.repoItem.value = args.repo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContributorAdapter(bindingComponent) { contribute ->
            println(contribute)
        }
        this.contributorAdapter = adapter

        with(viewDataBinding) {
            listContributor.adapter = contributorAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() = with(viewModel) {
        getContributions().observe(viewLifecycleOwner) { contributions ->
            contributorAdapter.submitList(contributions)
        }
    }
}