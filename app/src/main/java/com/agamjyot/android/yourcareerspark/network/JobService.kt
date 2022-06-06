package com.agamjyot.android.yourcareerspark.network

import com.agamjyot.android.yourcareerspark.models.JobResponse
import retrofit2.http.GET
import retrofit2.Call

interface JobService {

    @GET("remote-jobs")
    fun getJobResponse(): Call<JobResponse>
}