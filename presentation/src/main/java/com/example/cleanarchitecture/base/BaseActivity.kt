package com.example.cleanarchitecture.base

import android.support.v7.app.AppCompatActivity
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

}