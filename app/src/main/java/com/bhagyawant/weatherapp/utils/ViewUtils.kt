package com.bhagyawant.weatherapp.utils

import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT


fun Context.toast(message: String) {
    Toast.makeText(this, message, LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = VISIBLE
}

fun ProgressBar.hide() {
    visibility = GONE
}