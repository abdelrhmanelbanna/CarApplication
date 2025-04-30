package com.example.carapplication.GenerationScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Vehicle
import com.example.domain.usecases.GetVehicleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationViewModel @Inject constructor(
    val useCase: GetVehicleUseCase):ViewModel()
{
        val vehicleLiveData = MutableLiveData<List<Vehicle?>?>()

    fun getVehicle(
        attributeId: Int,
        vehicleId: Int,
        attributeValueId: Int){
        viewModelScope.launch {
            val result =  useCase.invoke(attributeId,vehicleId,attributeValueId)
            vehicleLiveData.postValue(result)
        }
    }

}