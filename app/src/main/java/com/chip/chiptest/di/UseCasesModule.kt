package com.chip.chiptest.di

import com.chip.chiptest.domain.usecase.GetBreedsImagesUseCase
import com.chip.chiptest.domain.usecase.GetBreedsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single {
        GetBreedsUseCase(dogsRepository = get())
    }
    single { GetBreedsImagesUseCase(dogsRepository =  get()) }
}