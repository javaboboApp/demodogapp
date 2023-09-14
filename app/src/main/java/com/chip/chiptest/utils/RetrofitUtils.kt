package com.chip.chiptest.utils

import com.chip.chiptest.utils.Api.BASE_URL
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {

    fun createBaseClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    inline fun <reified T> createRetrofit(
        okHttpClient: OkHttpClient
    ): T {
        val builder = Retrofit.Builder()
        return builder
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

}