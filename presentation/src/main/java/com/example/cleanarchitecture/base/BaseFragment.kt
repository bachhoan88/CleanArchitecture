package com.example.cleanarchitecture.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.domain.annotation.Action
import com.example.cleanarchitecture.domain.annotation.Redirect
import com.example.cleanarchitecture.util.Permission
import com.example.cleanarchitecture.util.autoCleared
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment(),
    EasyPermissions.PermissionCallbacks {

    abstract val bindingVariable: Int

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    var viewDataBinding by autoCleared<T>()

    private var toast: Toast? = null
    private var snackBar: Snackbar? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberException()
    }

    override fun onStop() {
        super.onStop()
        toast?.cancel()
        snackBar?.dismiss()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    internal fun hasPermission(@Size(min = 1) vararg permissions: String): Boolean {
        permissions.forEach {
            when (ContextCompat.checkSelfPermission(requireActivity(), it) != PackageManager.PERMISSION_GRANTED) {
                true -> return false
            }
        }
        return true
    }

    internal fun requestPermission(rationale: String, @Size(min = 1) vararg permissions: String) {
        Permission.requestPermissions(this, rationale, PERMISSION_REQUEST_CODE, permissions)
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    open fun permissionAccepted() {
    }

    private fun subscriberException() {
        viewModel.snackBarMessage.observe(viewLifecycleOwner, Observer {  message ->
            view?.let { snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT) }
            snackBar?.show()
        })
    }

    open fun positiveAction(@Action action: Int, data: Any? = null) { }

    open fun negativeAction(@Action action: Int, data: Any? = null) { }

    open fun redirectAction(@Redirect redirect: Int, data: Any? = null) { }

    open fun onBackPressed() {}

    companion object {
        private const val PERMISSION_REQUEST_CODE = Activity.RESULT_FIRST_USER + 1
    }
}