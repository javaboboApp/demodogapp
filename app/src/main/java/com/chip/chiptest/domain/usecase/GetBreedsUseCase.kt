package com.chip.chiptest.domain.usecase

import com.chip.chiptest.data.repository.IDogsRepository
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.utils.ApiResult


/**
 * Use case for fetching a list of dog breeds.
 *
 * @param dogsRepository The repository responsible for accessing dog-related data.
 */
class GetBreedsUseCase(private val dogsRepository: IDogsRepository) {

    /**
     * Executes the use case to fetch a list of dog breeds.
     *
     * @return An [ApiResult] containing the result of the operation.
     */
    suspend fun execute(): ApiResult<BreedsResponseDomain> {
        return dogsRepository.getBreeds()
    }
}
