package com.agamjyot.android.yourcareerspark.db

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fav_job")
data class FavJob(
    val candidateRequiredLocation: String?,
    val category: String?,
    val companyLogoUrl: String?,
    val companyName: String?,
    val description: String?,
    val id: Int?,
    val jobType: String?,
    val publicationDate: String?,
    val salary: String?,
    val tags: List<String>?,
    val title: String?,
    val url: String?
)