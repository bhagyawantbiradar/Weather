package com.bhagyawant.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhagyawant.weatherapp.data.db.entities.Bookmark

@Database(
    entities = [Bookmark::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBookmarksDao(): BookmarkDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "WeatherDatabase"
            ).build()

    }
}