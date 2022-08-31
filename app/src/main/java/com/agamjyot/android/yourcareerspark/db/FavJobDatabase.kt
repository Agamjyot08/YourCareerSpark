package com.agamjyot.android.yourcareerspark.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavJob::class], version = 1)
abstract class FavJobDatabase : RoomDatabase() {

    abstract fun getFavJobDao(): FavJobDao

    companion object {
        @Volatile
        private var instance: FavJobDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavJobDatabase::class.java,
                "fav_job_db"
            ).build()
    }
}