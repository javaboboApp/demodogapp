package com.chip.chiptest.di

import com.chip.chiptest.presentation.viewmodels.DogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        DogViewModel(getBreedsUseCase = get(), getBreedsImagesUseCase = get())
    }
}