package com.example.cleanarchitecture.binding

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment

class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    fun getFragmentBindingAdapters() = adapter
}
