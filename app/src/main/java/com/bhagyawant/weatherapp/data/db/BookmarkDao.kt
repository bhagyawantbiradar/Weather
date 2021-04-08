package com.bhagyawant.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bhagyawant.weatherapp.data.db.entities.Bookmark

@Dao
interface BookmarkDao {

    @Insert
    suspend fun insert(bookmark : Bookmark) : Long

    @Query("SELECT * FROM bookmark ORDER BY id DESC")
    suspend fun getBookmarks() : List<Bookmark>

    @Delete
    suspend fun delete(bookmark : Bookmark)


}