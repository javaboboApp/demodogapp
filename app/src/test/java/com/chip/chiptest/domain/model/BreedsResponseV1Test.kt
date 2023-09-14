package com.chip.chiptest.domain.model

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test


class BreedsResponseV1Test{
    @Test
    fun `asDomain should convert BreedsResponseV1 to BreedsResponseDomain correctly`() {
        // Arrange
        val gson = Gson()
        val json = """{"message": {"breed1": ["sub1", "sub2"], "breed2": ["sub3"]}, "status": "success"}"""
        val breedsResponse = gson.fromJson(json, BreedsResponseV1::class.java)

        // Act
        val result = breedsResponse.asDomain()

        // Assert
        val expected = BreedsResponseDomain(listOf("breed1", "breed2"))
        assertEquals(expected, result)
    }

    @Test
    fun `asDomain should handle null breedMap in BreedsResponseV1`() {
        // Arrange
        val gson = Gson()
        val json = """{"message": null, "status": "success"}"""
        val breedsResponse = gson.fromJson(json, BreedsResponseV1::class.java)

        // Act
        val result = breedsResponse.asDomain()

        // Assert
        val expected = BreedsResponseDomain(null)
        assertEquals(expected, result)
    }



}