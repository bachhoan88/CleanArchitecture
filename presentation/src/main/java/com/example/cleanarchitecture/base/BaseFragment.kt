package com.example.cleanarchitecture.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.util.Permission
import com.example.cleanarchitecture.util.autoCleared
import dagger.android.support.DaggerFragment
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

const val PERMISSION_REQUEST_CODE = Activity.RESULT_FIRST_USER + 1

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment(),
    EasyPermissions.PermissionCallbacks {

    abstract val bindingVariable: Int

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    var viewDataBinding by autoCleared<T>()

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

    open fun onBackPressed() {}
}