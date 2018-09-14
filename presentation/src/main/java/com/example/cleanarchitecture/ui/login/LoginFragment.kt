package com.example.cleanarchitecture.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.cleanarchitecture.BR
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.BaseFragment
import com.example.cleanarchitecture.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {


    companion object {
        fun newInstance() = LoginFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_login

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
