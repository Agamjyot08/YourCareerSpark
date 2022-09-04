package com.agamjyot.android.yourcareerspark.di

import android.content.Context
import androidx.room.Room
import com.agamjyot.android.yourcareerspark.db.FavJobDao
import com.agamjyot.android.yourcareerspark.db.FavJobDatabase
import com.agamjyot.android.yourcareerspark.network.JobService
import com.agamjyot.android.yourcareerspark.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAuthApi(instance: RetrofitInstance): JobService {
        return instance.buildApi(JobService::class.java)
    }

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun database(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavJobDatabase::class.java,
        "FavJobDatabase"
    ).build()

    @Singleton
    @Provides
    fun Dao(db: FavJobDatabase) =
        db.getFavJobDao() // The reason we can implement a Dao for the database

}