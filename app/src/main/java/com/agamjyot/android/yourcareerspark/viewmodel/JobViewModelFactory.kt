package com.agamjyot.android.yourcareerspark.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agamjyot.android.yourcareerspark.repository.JobRepository

class JobViewModelFactory(
    val app: Application,
    private val remoteJobRepository: JobRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JobViewModel(app, remoteJobRepository) as T
    }
}