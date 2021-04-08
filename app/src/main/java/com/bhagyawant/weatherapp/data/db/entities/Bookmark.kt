package com.bhagyawant.weatherapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(

    val city: String,
    val lat: String,
    val lon: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}