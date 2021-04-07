package com.bhagyawant.weatherapp.repositories

import com.bhagyawant.weatherapp.network.ApiInterface
import com.bhagyawant.weatherapp.network.responses.WeatherResponse
import retrofit2.Response

class WeatherRepository {

    suspend fun getWeatherForeCast(
        units: String,
        lat: String,
        lon: String,
        appid: String
    ): Response<WeatherResponse> {
        /*val weatherResponse = MutableLiveData<String>()

        ApiInterface().getWeatherForecast(lat,lon,appid).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    weatherResponse.value = response.body()?.string()
                }else{
                    weatherResponse.value = response.body()?.string()
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                weatherResponse.value = t.message
            }

        })

        return weatherResponse*/

        return ApiInterface().getWeatherForecast(units,lat, lon, appid)
    }


}
