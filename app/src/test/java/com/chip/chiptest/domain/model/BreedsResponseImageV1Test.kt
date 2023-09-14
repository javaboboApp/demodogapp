package com.chip.chiptest.domain.model

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test


class BreedsResponseImageV1Test{

    @Test
    fun `asDomain should convert BreedsResponseImageV1 to BreedsImageDomain correctly`() {
        // Arrange
        val gson = Gson()
        val json = """{"message": ["image1", "image2"], "status": "success"}"""
        val breedsResponse = gson.fromJson(json, BreedsResponseImageV1::class.java)

        // Act
        val result = breedsResponse.asDomain()

        // Assert
        val expected = BreedsImageDomain(listOf("image1", "image2"))
        assertEquals(expected, result)
    }

    @Test
    fun `asDomain should handle null images in BreedsResponseImageV1`() {
        // Arrange
        val gson = Gson()
        val json = """{"message": null, "status": "success"}"""
        val breedsResponse = gson.fromJson(json, BreedsResponseImageV1::class.java)

        // Act
        val result = breedsResponse.asDomain()

        // Assert
        val expected = BreedsImageDomain(null)
        assertEquals(expected, result)
    }


}