package com.chip.chiptest.di

import com.chip.chiptest.data.api.DogApi
import com.chip.chiptest.utils.RetrofitUtils
import com.chip.chiptest.utils.RetrofitUtils.createRetrofit
import org.koin.dsl.module

val apiModule = module {
    factory { provideDogApiService() }
}

fun provideDogApiService(): DogApi = createRetrofit(
    RetrofitUtils.createBaseClient()
        .build()
)