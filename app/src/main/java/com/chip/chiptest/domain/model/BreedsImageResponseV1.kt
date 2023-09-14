package com.chip.chiptest.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for fetching dog breed images from the API (Version 1).
 *
 * @param images A list of image URLs.
 * @param status The status of the response.
 */
data class BreedsResponseImageV1(
    @SerializedName("message") val images: List<String>? = null,
    @SerializedName("status") val status: String? = null
)
/**
 * Extension function to convert [BreedsResponseImageV1] to a [BreedsImageDomain] object.
 *
 * @return A [BreedsImageDomain] object.
 */
fun BreedsResponseImageV1.asDomain() = BreedsImageDomain(images)

/**
 * Data class representing dog breed images in the domain model.
 *
 * @param images A list of image URLs.
 */
data class BreedsImageDomain(val images: List<String>?)