package com.agamjyot.android.yourcareerspark.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.repository.JobRepository
import kotlinx.coroutines.launch

class JobViewModel(
    app: Application,
    private val jobRepository: JobRepository
): AndroidViewModel(app) {

    fun jobResult() = jobRepository.jobResult()

    fun addFavJob(job: FavJob) = viewModelScope.launch {
        jobRepository.addFavJob(job)
    }

    fun deleteFavJob(job: FavJob) = viewModelScope.launch {
        jobRepository.deleteFavJob(job)
    }

    fun getAllFavJobs() = jobRepository.getAllFavJobs()
}