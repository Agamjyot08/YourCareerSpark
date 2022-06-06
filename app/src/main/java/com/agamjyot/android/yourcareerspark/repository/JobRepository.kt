package com.agamjyot.android.yourcareerspark.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.agamjyot.android.yourcareerspark.models.JobResponse
import com.agamjyot.android.yourcareerspark.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobRepository {

    private val jobService = RetrofitInstance.apiService
    private val jobResponse: MutableLiveData<JobResponse> = MutableLiveData()

    init {
        getJobResponse()
    }

    private fun getJobResponse() {
        jobService.getJobResponse().enqueue(
            object : Callback<JobResponse> {
                override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                    jobResponse.postValue(response.body())
                }

                override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                    jobResponse.postValue(null)
                    Log.e("api error", "onFailure: ${t.message}")
                }
            }
        )
    }

        fun jobResult(): LiveData<JobResponse>{
            return jobResponse
        }

}