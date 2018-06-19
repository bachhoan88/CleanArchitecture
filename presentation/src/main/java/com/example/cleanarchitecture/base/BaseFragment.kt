package com.example.cleanarchitecture.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.util.autoCleared
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    abstract val bindingVariable: Int

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    var viewDataBinding by autoCleared<T>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun hideKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    fun showKeyboard(editText: EditText?) {
        if (editText == null) return

        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun findFragment(TAG: String): Fragment? {
        return fragmentManager!!.findFragmentByTag(TAG)
    }

    fun replaceFragment(fragment: Fragment, TAG: String?, transit: Int?) {
        val transaction = fragmentManager!!.beginTransaction()
                .replace(R.id.container, fragment, TAG)

        transit?.let { if (it != -1) transaction.setTransition(it)}
        transaction.commitNow()
    }

    override fun onAttach(context: Context?) {
        performDependencyInjection()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, viewModel)
        viewDataBinding.executePendingBindings()
        viewDataBinding.setLifecycleOwner(this)
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }
}