package com.example.cleanarchitecture.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import com.example.cleanarchitecture.R

object CommonUtils {
    fun showLoadingDialog(context: Context): AlertDialog {
        val alertDialog = AlertDialog.Builder(context).setView(R.layout.progress_dialog).create()
        alertDialog.show()

        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)

        return alertDialog
    }

}
