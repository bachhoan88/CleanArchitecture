package com.example.cleanarchitecture.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.example.cleanarchitecture.R

fun Fragment.showLoadingDialog(): AlertDialog =
        AlertDialog.Builder(activity!!).run {
            setView(R.layout.progress_dialog)
            setCancelable(false)
        }.create().apply {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }