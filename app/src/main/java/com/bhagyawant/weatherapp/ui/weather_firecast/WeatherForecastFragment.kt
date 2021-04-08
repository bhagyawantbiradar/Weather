package com.bhagyawant.weatherapp.ui.weather_firecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.utils.toast


class WeatherForecastFragment : Fragment() {

    var weatherResponse: String? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherResponse = arguments?.get("weather_response") as String?

        weatherResponse?.let { context?.toast(it) }

    }


}