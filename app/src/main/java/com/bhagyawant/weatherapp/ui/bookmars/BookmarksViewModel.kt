package com.bhagyawant.weatherapp.ui.bookmars

import android.view.View
import androidx.lifecycle.ViewModel
import com.bhagyawant.weatherapp.network.ApiUrls
import com.bhagyawant.weatherapp.repositories.WeatherRepository
import com.bhagyawant.weatherapp.utils.Coroutines

class BookmarksViewModel : ViewModel() {

    var weatherApiListener: WeatherApiListener? = null

    fun onItemClicked(view: View) {


    }

    fun onAddBookmarkClicked(view: View) {
        Coroutines.main {
            val response = WeatherRepository().getWeatherForeCast(
                ApiUrls.METRIC,
                ApiUrls.DUMMY_LAT,
                ApiUrls.DUMMY_LONG,
                ApiUrls.OPEN_WEATHER_MP_API_KEY
            )
            if (response.isSuccessful) {
                response.body()?.let { weatherApiListener?.onSuccess(it) }
            }else{
                weatherApiListener?.onFailure()
            }
        }

    }

}