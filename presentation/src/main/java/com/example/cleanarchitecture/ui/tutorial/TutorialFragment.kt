package com.example.cleanarchitecture.ui.tutorial

import androidx.lifecycle.ViewModelProviders
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.databinding.FragmentTutorialBinding

class TutorialFragment : BaseFragment<FragmentTutorialBinding, TutorialViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_tutorial

    override val viewModel: TutorialViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(TutorialViewModel::class.java)
}
