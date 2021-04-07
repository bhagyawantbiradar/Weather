package com.bhagyawant.weatherapp.network.responses

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val threeHourlyVolume: Double
)