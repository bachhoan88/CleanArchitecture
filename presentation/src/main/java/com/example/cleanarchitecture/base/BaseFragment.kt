package com.example.cleanarchitecture.base

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.LayoutRes
import android.support.annotation.Size
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.util.autoCleared
import dagger.android.support.AndroidSupportInjection
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

const val PERMISSION_REQUEST_CODE = Activity.RESULT_FIRST_USER + 1

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(),
        EasyPermissions.PermissionCallbacks {

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
            dismissKeyboard(view.windowToken)
        }
    }

    fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    fun showKeyboard(editText: EditText?) {
        if (editText == null) return

        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun findFragment(TAG: String): Fragment? {
        return activity!!.supportFragmentManager.findFragmentByTag(TAG)
    }

    fun replaceFragment(fragment: Fragment, TAG: String?, addToBackStack: Boolean? = false, transit: Int? = -1) {
        val transaction = activity!!.supportFragmentManager!!.beginTransaction()
                .replace(R.id.container, fragment)

        addToBackStack?.let { if (it) transaction.addToBackStack(TAG) }
        transit?.let { if (it != -1) transaction.setTransition(it) }
        transaction.commit()
    }

    override fun onAttach(context: Context?) {
        performDependencyInjection()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            setLifecycleOwner(this@BaseFragment)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    fun hasPermission(@Size(min = 1) vararg permissions: String): Boolean {
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(activity!!, perm) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }

        return true
    }

    fun requestPermission(rationale: String, @Size(min = 1) vararg permissions: String) {
        for (perm in permissions) {
            EasyPermissions.requestPermissions(this, rationale, PERMISSION_REQUEST_CODE, perm)
        }
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    open fun permissionAccepted() {

    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }
}