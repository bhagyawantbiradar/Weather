package com.bhagyawant.weatherapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhagyawant.weatherapp.network.ApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    fun getWeatherForeCast(lat: String, lon: String, appid: String): LiveData<String> {
         val weatherResponse = MutableLiveData<String>()

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

        return weatherResponse
    }


}
