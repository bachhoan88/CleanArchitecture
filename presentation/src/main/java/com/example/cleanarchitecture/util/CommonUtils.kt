package com.example.cleanarchitecture.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import com.example.cleanarchitecture.R

fun showLoadingDialog(context: Context): AlertDialog {
    val alertDialog = AlertDialog.Builder(context).setView(R.layout.progress_dialog).create()

    alertDialog.apply {
        show()
        if (window != null) {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    return alertDialog
}
