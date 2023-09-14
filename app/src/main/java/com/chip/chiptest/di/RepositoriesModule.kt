package com.chip.chiptest.di

import com.chip.chiptest.data.repository.DogsRepository
import com.chip.chiptest.data.repository.IDogsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<IDogsRepository>{
        DogsRepository(dogsApi = get())
    }
}