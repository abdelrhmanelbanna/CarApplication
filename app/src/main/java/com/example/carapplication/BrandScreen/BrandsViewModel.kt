package com.example.carapplication.BrandScreen

import android.util.Log
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

    private val _brandsState = MutableLiveData<BrandState>()
    val brandsState: LiveData<BrandState> = _brandsState
    private var fullBrandList: List<Brand> = emptyList()

    fun getBrands(category: Int = 1) {
        Log.d("BrandsViewModel", "Starting getBrands for category: $category")
        _brandsState.postValue(BrandState.Loading)
        viewModelScope.launch {
            try {
                val brands = brandsUseCase.invoke() ?: emptyList()
                Log.d("BrandsViewModel", "Received brands: $brands")
                fullBrandList = brands
                _brandsState.postValue(BrandState.Success(brands))
            } catch (e: Exception) {
                Log.e("BrandsViewModel", "Error fetching brands", e)
                _brandsState.postValue(BrandState.Error(e.message ?: "Failed to load brands"))
            }
        }
    }
    fun filterBrands(query: String) {
        Log.d("BrandsViewModel", "Filtering brands with query: $query")
        if (query.isEmpty()) {
            _brandsState.postValue(BrandState.Success(fullBrandList))
        } else {
            val filteredList = fullBrandList.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _brandsState.postValue(BrandState.Success(filteredList))
        }
    }

}
sealed class BrandState {
    object Loading : BrandState()
    data class Success(val brands: List<Brand>) : BrandState()
    data class Error(val message: String) : BrandState()
}