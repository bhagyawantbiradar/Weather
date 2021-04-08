package com.bhagyawant.weatherapp.ui.bookmars

import com.bhagyawant.weatherapp.network.responses.WeatherResponse

interface WeatherApiListener {

    fun onStarted()

    fun onSuccess(weatherResponse: WeatherResponse)

    fun onFailure(message : String)
}