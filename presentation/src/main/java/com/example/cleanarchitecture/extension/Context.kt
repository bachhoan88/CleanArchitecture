package com.example.cleanarchitecture.extension

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.showDialog(title: String? = null, message: String? = null, cancelable: Boolean? = false,
                       positiveMessage: String? = null, positiveAction: (() -> Unit)? = null,
                       negativeMessage: String? = null, negativeAction: (() -> Unit)? = null) {
    AlertDialog.Builder(this).apply {
        title?.let { setTitle(it) }
        cancelable?.let { setCancelable(it) }
        message?.let { setMessage(message) }
        positiveMessage?.let { setPositiveButton(it) { _, _ -> positiveAction?.invoke() } }
        negativeMessage?.let { setNegativeButton(it) { _, _ -> negativeAction?.invoke() } }
    }.create().show()
}