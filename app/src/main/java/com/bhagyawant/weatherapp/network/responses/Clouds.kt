package com.bhagyawant.weatherapp.network.responses
import com.google.gson.annotations.SerializedName

data class Clouds (

	@SerializedName("all") val all : Int
)