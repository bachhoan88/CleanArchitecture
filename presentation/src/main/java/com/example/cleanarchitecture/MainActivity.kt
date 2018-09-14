package com.example.cleanarchitecture

import android.os.Bundle
import com.example.cleanarchitecture.base.BaseActivity
import com.example.cleanarchitecture.ui.main.MainFragment
import dagger.android.support.HasSupportFragmentInjector

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    override fun firstFragment() = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, firstFragment())
                    .commitNow()
        }
    }
}
