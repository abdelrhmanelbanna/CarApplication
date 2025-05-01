package com.example.carapplication.BrandScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Brand
import com.example.domain.usecases.GetBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val brandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private val _brandsLiveData = MutableLiveData<List<Brand>>()
    val brandsLiveData: LiveData<List<Brand>> get() = _brandsLiveData
    private var fullBrandList: List<Brand> = emptyList()

    fun getBrands() {
        viewModelScope.launch {
            try {
                viewModelScope.launch {
                    val brands = brandsUseCase.invoke() // Assuming this fetches brands
                    brands?.let {
                        fullBrandList = it
                        _brandsLiveData.postValue(it)
                    }
                }
            } catch (ex: Exception) {

            }
        }
    }
    fun filterBrands(query: String) {
        if (query.isEmpty()) {
            _brandsLiveData.postValue(fullBrandList)
        } else {
            val filteredList = fullBrandList.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _brandsLiveData.postValue(filteredList)
        }
    }

}