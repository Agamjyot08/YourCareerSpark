package com.agamjyot.android.yourcareerspark.network

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") var data: T?
)