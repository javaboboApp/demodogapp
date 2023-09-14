package com.chip.chiptest.domain.usecase

import com.chip.chiptest.data.repository.IDogsRepository
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetBreedsImagesUseCaseTest{
    private lateinit var getBreedsImagesUseCase: GetBreedsImagesUseCase
    private val dogsRepository: IDogsRepository = mockk()

    @Before
    fun setup() {
        getBreedsImagesUseCase = GetBreedsImagesUseCase(dogsRepository)
    }

    @Test
    fun `execute should return Success when repository call is successful`() = runBlocking {
        // Arrange
        val breed = "breed1"
        val numRandomImages = 5
        val mockResponse = BreedsImageDomain(listOf("image1", "image2"))

        coEvery { dogsRepository.getBreedsImage(breed, numRandomImages) } returns ApiResult.Success(mockResponse)

        // Act
        val result = getBreedsImagesUseCase.execute(breed, numRandomImages)

        // Assert
        assertEquals(ApiResult.Success(mockResponse), result)
    }

    @Test
    fun `execute should return Error when repository call fails`() = runBlocking {
        // Arrange
        val breed = "breed1"
        val numRandomImages = 5
        val mockError = Exception("Repository Error")
        coEvery { dogsRepository.getBreedsImage(breed, numRandomImages) } returns ApiResult.Error(mockError)

        // Act
        val result = getBreedsImagesUseCase.execute(breed, numRandomImages)

        // Assert
        assertEquals(ApiResult.Error(mockError), result)
    }
}