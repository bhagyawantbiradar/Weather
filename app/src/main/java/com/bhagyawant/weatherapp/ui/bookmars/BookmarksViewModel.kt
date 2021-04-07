package com.bhagyawant.weatherapp.ui.bookmars

import android.view.View
import androidx.lifecycle.ViewModel
import com.bhagyawant.weatherapp.repositories.WeatherRepository

class BookmarksViewModel : ViewModel() {

    var weatherApiListener : WeatherApiListener? = null

    fun onItemClicked(view: View) {

        val weatherResponse = WeatherRepository().getWeatherForeCast("","","")
        weatherApiListener?.onSuccess(weatherResponse)
    }

    fun onAddBookmarkClicked(view: View){

    }

}