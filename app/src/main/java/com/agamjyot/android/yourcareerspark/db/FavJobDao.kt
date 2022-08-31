package com.agamjyot.android.yourcareerspark.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavJobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavJob(job: FavJob)

    @Query("SELECT * FROM fav_job ORDER BY id DESC")
    fun getAllFavJobs(): LiveData<List<FavJob>>

    @Delete
    suspend fun deleteFavJob(job: FavJob)
}