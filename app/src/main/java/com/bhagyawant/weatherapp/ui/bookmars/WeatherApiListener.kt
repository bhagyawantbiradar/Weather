package com.bhagyawant.weatherapp.ui.bookmars

import androidx.lifecycle.LiveData

interface WeatherApiListener {

    fun onStarted()

    fun onSuccess(weatherResponse: LiveData<String>)

    fun onFailure()
}