package com.chip.chiptest.domain.model

import com.google.gson.annotations.SerializedName


/**
 * Data class representing the response for fetching dog breeds from the API (Version 1).
 *
 * @param breedMap A map of breeds to their sub-breeds.
 * @param status The status of the response.
 */
data class BreedsResponseV1(
    @SerializedName("message") val breedMap: Map<String, List<String>>? = null,
    @SerializedName("status") val status: String? = null
)

/**
 * Extension function to convert [BreedsResponseV1] to a [BreedsResponseDomain] object.
 *
 * @return A [BreedsResponseDomain] object.
 */
fun BreedsResponseV1.asDomain() = BreedsResponseDomain(breedMap?.keys?.toList())

/**
 * Data class representing a list of dog breeds in the domain model.
 *
 * @param listOfBreeds A list of dog breeds.
 */
data class BreedsResponseDomain(val listOfBreeds: List<String>?)
