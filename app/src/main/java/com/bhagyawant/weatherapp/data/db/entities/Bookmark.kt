package com.bhagyawant.weatherapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var city: String? = null,
    var lat: String? = null,
    var lon: String?
)