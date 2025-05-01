package com.example.carapplication.ModelScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Models
import com.example.domain.usecases.GetModelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ModelsViewModel @Inject constructor(
    private val modelsUseCase: GetModelsUseCase
) : ViewModel() {

    private val _modelState = MutableLiveData<ModelState>()
    val modelState: LiveData<ModelState> = _modelState

    private var currentPage = 1
    private val pageSize = 10
    private var brandId: Int = -1
    private var allModels: MutableList<Models> = mutableListOf()
    var canLoadMore: Boolean = true
        private set
    var isLoading: Boolean = false
        private set

    fun loadModels(brandId: Int) {
        this.brandId = brandId
        currentPage = 1
        allModels.clear()
        canLoadMore = true
        fetchModels()
    }

    fun loadNextPage() {
        if (!canLoadMore || isLoading) return
        currentPage++
        fetchModels()
    }

    private fun fetchModels() {
        isLoading = true
        _modelState.postValue(ModelState.Loading)
        viewModelScope.launch {
            try {
                val newModels = modelsUseCase.invoke(brandId, currentPage, pageSize)
                if (newModels.isEmpty() || newModels.size < pageSize) {
                    canLoadMore = false
                }
                allModels.addAll(newModels)
                _modelState.postValue(ModelState.Success(allModels.toList()))
            } catch (e: Exception) {
                _modelState.postValue(ModelState.Error(e.message ?: "Failed to load models"))
            } finally {
                isLoading = false
            }
        }
    }
}

sealed class ModelState {
    object Loading : ModelState()
    data class Success(val models: List<Models>) : ModelState()
    data class Error(val message: String) : ModelState()
}