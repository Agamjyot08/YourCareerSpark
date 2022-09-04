package com.agamjyot.android.yourcareerspark.di

import com.agamjyot.android.yourcareerspark.network.JobService
import com.agamjyot.android.yourcareerspark.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAuthApi(instance: RetrofitInstance): JobService {
        return instance.buildApi(JobService::class.java)
    }

}