package com.chip.chiptest.data.repository

import com.chip.chiptest.data.api.DogApi
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.domain.model.asDomain
import com.chip.chiptest.utils.ApiResult
import com.haroldadmin.cnradapter.NetworkResponse

/**
 * Repository interface for accessing dog-related data.
 */
interface IDogsRepository {

    /**
     * Fetches a list of dog breeds.
     *
     * @return An [ApiResult] containing the result of the operation.
     */
    suspend fun getBreeds(): ApiResult<BreedsResponseDomain>

    /**
     * Fetches random images of a specific dog breed.
     *
     * @param breed The breed for which to fetch random images.
     * @param numRandomImages The number of random images to fetch.
     * @return An [ApiResult] containing the result of the operation.
     */
    suspend fun getBreedsImage(breed: String, numRandomImages: Int): ApiResult<BreedsImageDomain>
}

/**
 * Implementation of [IDogsRepository] for accessing dog-related data.
 *
 * @param dogsApi The API client for dog-related endpoints.
 */

class DogsRepository(private val dogsApi: DogApi) : IDogsRepository {

    override suspend fun getBreeds(): ApiResult<BreedsResponseDomain> {
        return when (val breedsResponse = dogsApi.getBreeds()) {
            is NetworkResponse.Success -> {
                ApiResult.Success(breedsResponse.body.asDomain())
            }

            is NetworkResponse.Error -> {
                ApiResult.Error(breedsResponse.error)
            }
        }
    }

    override suspend fun getBreedsImage(breed: String, numRandomImages: Int): ApiResult<BreedsImageDomain> {
        return when (val breedsImagesResponse = dogsApi.getRandomImages(breed, numRandomImages)) {
            is NetworkResponse.Success -> {
                ApiResult.Success(breedsImagesResponse.body.asDomain())
            }

            is NetworkResponse.Error -> {
                ApiResult.Error(breedsImagesResponse.error)
            }
        }
    }


}