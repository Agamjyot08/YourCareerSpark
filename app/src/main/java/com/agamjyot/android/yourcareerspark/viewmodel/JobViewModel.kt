package com.agamjyot.android.yourcareerspark.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.agamjyot.android.yourcareerspark.repository.JobRepository

class JobViewModel(
    app: Application,
    private val jobRepository: JobRepository
): AndroidViewModel(app) {

    fun jobResult() = jobRepository.jobResult()
}