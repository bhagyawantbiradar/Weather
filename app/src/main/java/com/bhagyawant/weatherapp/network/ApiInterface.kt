package com.bhagyawant.weatherapp.network

import com.bhagyawant.weatherapp.network.ApiUrls.Companion.BASE_URL
import com.bhagyawant.weatherapp.network.ApiUrls.Companion.WEATHER
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(WEATHER)
    fun getWeatherForecast(
        @Query ("lat") lat : String,
        @Query ("lon") lon : String,
        @Query ("appid") appid : String) : Call<ResponseBody>

    companion object{
        operator fun invoke() : ApiInterface{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}