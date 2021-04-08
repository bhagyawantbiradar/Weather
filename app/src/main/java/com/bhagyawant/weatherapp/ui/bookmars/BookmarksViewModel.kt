package com.bhagyawant.weatherapp.ui.bookmars

import androidx.lifecycle.ViewModel
import com.bhagyawant.weatherapp.data.db.entities.Bookmark
import com.bhagyawant.weatherapp.network.ApiUrls
import com.bhagyawant.weatherapp.repositories.WeatherRepository
import com.bhagyawant.weatherapp.utils.Coroutines

class BookmarksViewModel : ViewModel() {

    var weatherApiListener: WeatherApiListener? = null

    fun getWeatherForecast(bookmark: Bookmark) {
        Coroutines.main {
            weatherApiListener?.onStarted()
            val response = WeatherRepository().getWeatherForeCast(
                ApiUrls.METRIC,
                bookmark.lat,
                bookmark.lon,
                ApiUrls.OPEN_WEATHER_MP_API_KEY
            )
            if (response.isSuccessful) {
                response.body()?.let { weatherApiListener?.onSuccess(it) }
            } else {
                weatherApiListener?.onFailure("Failed to load weather")
            }
        }
    }

}