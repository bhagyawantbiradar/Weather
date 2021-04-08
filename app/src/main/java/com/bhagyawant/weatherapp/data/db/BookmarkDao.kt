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
    fun insert(bookmark : Bookmark) : Long

    @Query("SELECT * FROM bookmark")
    fun getBookmarks() : LiveData<List<Bookmark>>

    @Delete
    fun delete(bookmark : Bookmark)


}