package com.tomoima.debot.util

import android.app.Activity
import android.app.AlertDialog

object DialogUtil {
    fun showDialog(activity: Activity, title: String?, message: String?) {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        if (!title.isNullOrBlank()) {
            alertDialogBuilder.setTitle(title)
        }
        if (!message.isNullOrBlank()) {
            alertDialogBuilder.setMessage(message)
        }
        alertDialogBuilder.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        alertDialogBuilder.setCancelable(true)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
