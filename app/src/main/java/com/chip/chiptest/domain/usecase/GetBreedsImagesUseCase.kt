package com.chip.chiptest.domain.usecase

import com.chip.chiptest.data.repository.IDogsRepository
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.utils.ApiResult

/**
 * Use case for fetching random dog images of a specific breed.
 *
 * @param dogsRepository The repository responsible for accessing dog-related data.
 */
class GetBreedsImagesUseCase(private val dogsRepository: IDogsRepository) {

    /**
     * Executes the use case to fetch random images of a specific dog breed.
     *
     * @param breed The breed for which to fetch random images.
     * @param numRandomImages The number of random images to fetch.
     * @return An [ApiResult] containing the result of the operation.
     */
    suspend fun execute(breed: String, numRandomImages: Int): ApiResult<BreedsImageDomain> {
        return dogsRepository.getBreedsImage(breed, numRandomImages)
    }

}