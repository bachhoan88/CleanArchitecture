package com.example.cleanarchitecture.ui.tutorial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.databinding.FragmentTutorialBinding

class TutorialFragment : BaseFragment<FragmentTutorialBinding, TutorialViewModel>() {
    companion object {
        fun newInstance() = TutorialFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_tutorial

    override val viewModel: TutorialViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(TutorialViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
