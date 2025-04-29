package com.example.carapplication.ModelScreen

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
    val modelsUseCase: GetModelsUseCase
) :ViewModel() {

    val modelsLivedata = MutableLiveData<List<Models?>?>()

    fun getModels(brandId : Int){
        viewModelScope.launch {
            try{
                val result = modelsUseCase.invoke(brandId)
                modelsLivedata.postValue(result)
            }
            catch (ex:Exception){
                throw ex
            }



        }

    }
}