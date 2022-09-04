package com.agamjyot.android.yourcareerspark.network

import com.agamjyot.android.yourcareerspark.models.JobResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response

interface JobService {

    @GET("remote-jobs")
    fun getJobResponse(): JobResponse
}