package com.bhagyawant.weatherapp.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT


fun Context.toast(message: String) {
    Toast.makeText(this, message, LENGTH_SHORT).show()
}
