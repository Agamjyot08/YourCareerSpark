package com.agamjyot.android.yourcareerspark.network

import com.agamjyot.android.yourcareerspark.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitInstance @Inject constructor(){

    companion object {
        private const val BASE_URL = "https://remotive.com/"
    }

    fun <Api> buildApi(api: Class<Api>): Api {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) // same for .addInterceptor(...)
            .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }
}