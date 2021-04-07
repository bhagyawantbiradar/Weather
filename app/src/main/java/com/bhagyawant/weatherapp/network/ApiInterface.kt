package com.bhagyawant.weatherapp.network

import com.bhagyawant.weatherapp.network.ApiUrls.Companion.BASE_URL
import com.bhagyawant.weatherapp.network.ApiUrls.Companion.WEATHER
import com.bhagyawant.weatherapp.network.responses.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(WEATHER)
    suspend fun getWeatherForecast(
        @Query("units") units: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<WeatherResponse>

    companion object {


        operator fun invoke(): ApiInterface {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY


            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiInterface::class.java)
        }
    }
}