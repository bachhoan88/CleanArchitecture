package com.example.cleanarchitecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cleanarchitecture.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    abstract fun firstFragment(): BaseFragment<*, *>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        ((supportFragmentManager.findFragmentById(R.id.container)) as BaseFragment<*, *>).onBackPressed()
        super.onBackPressed()
    }

    override fun supportFragmentInjector() = fragmentInjector
}