package com.agamjyot.android.yourcareerspark.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agamjyot.android.yourcareerspark.db.FavJob
import com.agamjyot.android.yourcareerspark.models.JobResponse
import com.agamjyot.android.yourcareerspark.utils.Resource
import com.agamjyot.android.yourcareerspark.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor (private var jobRepository: JobRepository): ViewModel() {

    private val _getDataRes = MutableStateFlow<Resource<JobResponse>>(Resource.Initial)
    val getDataRes: StateFlow<Resource<JobResponse>> = _getDataRes.asStateFlow()

    fun getJobs() = viewModelScope.launch {
        _getDataRes.value = Resource.Loading
        _getDataRes.value = jobRepository.getJobResponse()
    }

    fun addFavJob(job: FavJob) = viewModelScope.launch {
        jobRepository.addFavJob(job)
    }

    fun deleteFavJob(job: FavJob) = viewModelScope.launch {
        jobRepository.deleteFavJob(job)
    }

    fun getAllFavJobs() = jobRepository.getAllFavJobs()
}