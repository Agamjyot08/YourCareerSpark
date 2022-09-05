package com.agamjyot.android.yourcareerspark.network

import com.agamjyot.android.yourcareerspark.models.JobResponse
import retrofit2.http.GET

interface JobService {

    @GET("remote-jobs")
    suspend fun getJobResponse(): JobResponse
}