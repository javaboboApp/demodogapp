package com.chip.chiptest.data.api

import com.chip.chiptest.domain.model.BreedsResponseImageV1
import com.chip.chiptest.domain.model.BreedsResponseV1
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface representing a Dog API for retrieving information about dog breeds.
 */
interface DogApi {

    /**
     * Sends an HTTP GET request to retrieve information about dog breeds.
     *
     * @return A [NetworkResponse] containing the response data of type [BreedsResponseV1] on success,
     *         or an error on failure (type [Unit], we consider only 4xx or 5xx without body).
     */

    @GET("/api/breeds/list/all")
    suspend fun getBreeds(): NetworkResponse<BreedsResponseV1, Unit>

    /**
     * Fetches random images of a specific dog breed.
     *
     * @param breed The breed for which to fetch random images.
     * @param numRandomImages The number of random images to fetch.
     * @return A [NetworkResponse] containing the response data of type [BreedsResponseImageV1] on success,
     *         or an error on failure (type [Unit], we consider only 4xx or 5xx without body).
     */
    @GET("api/breed/{breed_id}/images/random/{numRandomImages}")
    suspend fun getRandomImages(@Path("breed_id") breed: String,@Path("numRandomImages") numRandomImages: Int): NetworkResponse<BreedsResponseImageV1 , Unit>

}