package com.chip.chiptest.data.repository

import com.chip.chiptest.data.api.DogApi
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.domain.model.BreedsResponseImageV1
import com.chip.chiptest.domain.model.BreedsResponseV1
import com.chip.chiptest.utils.ApiResult
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException


class DogsRepositoryTest {

    private lateinit var dogsRepository: DogsRepository
    private val dogApi: DogApi = mockk()

    @Before
    fun setup() {
        dogsRepository = DogsRepository(dogApi)
    }

    @Test
    fun `getBreeds should return Success when API call is successful`() = runBlocking {
        // Arrange
        val mockResponse = BreedsResponseV1(mapOf("breed1" to emptyList()), "success")
        val expectedResult = ApiResult.Success(BreedsResponseDomain(listOf("breed1")))
        coEvery { dogApi.getBreeds() } returns NetworkResponse.Success(mockResponse, Response.success(200, mockResponse) )

        // Act
        val result = dogsRepository.getBreeds()

        // Assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getBreeds should return Error when API call fails`() = runBlocking {
        // Arrange
        val mockError = IOException("API Error")
        val expectedResult =ApiResult.Error(mockError)
        coEvery { dogApi.getBreeds() } returns NetworkResponse.NetworkError(mockError)

        // Act
        val result = dogsRepository.getBreeds()

        // Assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getBreedsImage should return Success when API call is successful`() = runBlocking {
        // Arrange
        val breed = "breed1"
        val numRandomImages = 5
        val mockResponse = BreedsResponseImageV1(listOf("image1", "image2"), "success")
        val expectedResult = ApiResult.Success(BreedsImageDomain(listOf("image1", "image2")))
        coEvery { dogApi.getRandomImages(breed, numRandomImages) } returns NetworkResponse.Success(mockResponse, Response.success(200, mockResponse))

        // Act
        val result = dogsRepository.getBreedsImage(breed, numRandomImages)

        // Assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getBreedsImage should return Error when API call fails`() = runBlocking {
        // Arrange
        val breed = "breed1"
        val numRandomImages = 5
        val mockError = IOException("API Error")
        val expectedResult = ApiResult.Error(mockError)
        coEvery { dogApi.getRandomImages(breed, numRandomImages) } returns NetworkResponse.NetworkError(mockError)

        // Act
        val result = dogsRepository.getBreedsImage(breed, numRandomImages)

        // Assert
        assertEquals(expectedResult, result)
    }

}