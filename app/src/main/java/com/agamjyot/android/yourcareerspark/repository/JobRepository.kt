package com.agamjyot.android.yourcareerspark.repository

import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.db.FavJobDatabase
import com.agamjyot.android.yourcareerspark.network.JobService
import com.agamjyot.android.yourcareerspark.network.SafeApiCall
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val api: JobService,
    private val db: FavJobDatabase
) : SafeApiCall {

    suspend fun getJobResponse() = safeApiCall {
        api.getJobResponse()
    }

    suspend fun addFavJob(job: FavJob) = db.getFavJobDao().addFavJob(job)
    suspend fun deleteFavJob(job: FavJob) = db.getFavJobDao().deleteFavJob(job)
    fun getAllFavJobs() = db.getFavJobDao().getAllFavJobs()
}