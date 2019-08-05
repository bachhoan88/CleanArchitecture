package com.example.cleanarchitecture.extension

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.cleanarchitecture.R

fun Fragment.showDialogLoading(): AlertDialog =
        AlertDialog.Builder(requireContext()).run {
            setView(R.layout.progress_dialog)
            setCancelable(false)
        }.create().apply {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

fun Fragment.showSoftKeyboard(windowToken: IBinder?, show: Boolean) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    when (show) {
        true -> imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        else -> imm.hideSoftInputFromWindow(windowToken, 0)
    }
}