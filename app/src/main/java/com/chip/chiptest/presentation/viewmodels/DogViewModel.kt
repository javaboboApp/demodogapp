package com.chip.chiptest.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chip.chiptest.domain.model.BreedsImageDomain
import com.chip.chiptest.domain.model.BreedsResponseDomain
import com.chip.chiptest.domain.model.BreedsResponseImageV1
import com.chip.chiptest.domain.usecase.GetBreedsImagesUseCase
import com.chip.chiptest.domain.usecase.GetBreedsUseCase
import com.chip.chiptest.utils.ApiResult
import kotlinx.coroutines.launch

/**
 * ViewModel for managing dog breed data and images.
 *
 * @param getBreedsUseCase Use case for fetching dog breeds.
 * @param getBreedsImagesUseCase Use case for fetching images of a specific breed.
 */
class DogViewModel(private val getBreedsUseCase: GetBreedsUseCase, private val getBreedsImagesUseCase: GetBreedsImagesUseCase) : ViewModel() {

    private val _breedsLiveData: MutableLiveData<ApiResult<BreedsResponseDomain>> = MutableLiveData()
    val breedsLiveData: LiveData<ApiResult<BreedsResponseDomain>> = _breedsLiveData

    private val _breedsImagesLiveData: MutableLiveData<ApiResult<BreedsImageDomain>> = MutableLiveData()
    val breedsImagesLiveData: LiveData<ApiResult<BreedsImageDomain>> = _breedsImagesLiveData

    companion object{
        const val DEFAULT_NUM_RANDOM_IMAGES = 10
    }

    /**
     * Load a list of dog breeds.
     */
    fun loadBreeds() {
        _breedsLiveData.value = ApiResult.Loading
        viewModelScope.launch {
            val breeds = getBreedsUseCase.execute()
            _breedsLiveData.postValue(breeds)
        }
    }

    /**
     * Load random images for a specific breed.
     *
     * @param breed The breed for which to fetch random images.
     * @param numOfImages The number of random images to load (default is [DEFAULT_NUM_RANDOM_IMAGES]).
     */
    fun loadRandomImages(breed: String, numOfImages: Int = DEFAULT_NUM_RANDOM_IMAGES){
        _breedsImagesLiveData.value = ApiResult.Loading
        viewModelScope.launch {
            val breeds = getBreedsImagesUseCase.execute(breed, numOfImages)
            _breedsImagesLiveData.postValue(breeds)
        }

    }
}