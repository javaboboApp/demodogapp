package com.chip.chiptest.domain.usecase

import com.chip.chiptest.data.repository.IDogsRepository
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetBreedsUseCaseTest{
    private lateinit var getBreedsUseCase: GetBreedsUseCase
    private val dogsRepository: IDogsRepository = mockk()

    @Before
    fun setup() {
        getBreedsUseCase = GetBreedsUseCase(dogsRepository)
    }

    @Test
    fun `execute should return Success when repository call is successful`() = runBlocking {
        // Arrange
        val mockResponse = BreedsResponseDomain(listOf("breed1", "breed2"))
        coEvery { dogsRepository.getBreeds() } returns ApiResult.Success(mockResponse)

        // Act
        val result = getBreedsUseCase.execute()

        // Assert
        assertEquals(ApiResult.Success(mockResponse), result)
    }

    @Test
    fun `execute should return Error when repository call fails`() = runBlocking {
        // Arrange
        val mockError = Exception("Repository Error")
        coEvery { dogsRepository.getBreeds() } returns ApiResult.Error(mockError)

        // Act
        val result = getBreedsUseCase.execute()

        // Assert
        assertEquals(ApiResult.Error(mockError), result)
    }

}