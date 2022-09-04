package com.agamjyot.android.yourcareerspark.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.db.FavJobDatabase
import com.agamjyot.android.yourcareerspark.models.JobResponse
import com.agamjyot.android.yourcareerspark.network.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val api: JobService,
    private val db: FavJobDatabase
) : SafeApiCall {

//    private val jobService = RetrofitInstance.apiService
//    private val jobResponse: MutableLiveData<JobResponse> = MutableLiveData()


    //    private fun getJobResponse() {
//        jobService.getJobResponse().enqueue(
//            object : Callback<JobResponse> {
//                override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
//                    jobResponse.postValue(response.body())
//                }
//
//                override fun onFailure(call: Call<JobResponse>, t: Throwable) {
//                    jobResponse.postValue(null)
//                    Log.e("api error", "onFailure: ${t.message}")
//                }
//            }
//        )
//    }
//
//    fun jobResult(): LiveData<JobResponse> {
//        return jobResponse
//    }
    suspend fun getJobResponse() = safeApiCall {
        api.getJobResponse()
    }

    suspend fun addFavJob(job: FavJob) = db.getFavJobDao().addFavJob(job)
    suspend fun deleteFavJob(job: FavJob) = db.getFavJobDao().deleteFavJob(job)
    fun getAllFavJobs() = db.getFavJobDao().getAllFavJobs()
}