package com.agamjyot.android.yourcareerspark.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_job")
data class FavJob(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val candidateRequiredLocation: String?,
    val category: String?,
    val companyLogoUrl: String?,
    val companyName: String?,
    val description: String?,
    val jobId: Int?,
    val jobType: String?,
    val publicationDate: String?,
    val salary: String?,
    val title: String?,
    val url: String?
)