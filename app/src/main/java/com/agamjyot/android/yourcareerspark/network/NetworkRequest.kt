package com.agamjyot.android.yourcareerspark.network

import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Hritik Arthur on 23,December,2021
 */
object NetworkRequest {

    suspend fun <T> process(api: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = api()
            val code = response.code()
            val message = response.message()
            val body = response.body()

            return if (response.isSuccessful) {
                ApiResponse.Success(code, message, body)
            } else {
                val rawRes = response.errorBody()?.string() ?: "{}"
                val json = JSONObject(rawRes)
                val m = json.optString("message")
                ApiResponse.Failure(code, m, body)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Failure(-1, e.message ?: "Something went wrong!" , null, e)
        }
    }
}

/**
 * ApiResponse should not have loading state. Only Success or Failure
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val code: Int, val message: String, val data: T?) :
        ApiResponse<T>()

    data class Failure(val code: Int, val message: String, val data: Any? = null, val throwable: Throwable? = null) :
        ApiResponse<Nothing>()
}