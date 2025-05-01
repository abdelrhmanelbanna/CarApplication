package com.example.domain.usecases

import com.example.domain.model.Vehicle
import com.example.domain.repository.VehicleRepository
import javax.inject.Inject

class GetVehicleUseCase  @Inject constructor(
    val repository: VehicleRepository
){

    suspend fun invoke(
        attributeId: Int,
        vehicleId: Int,
        attributeValueId: Int
    ):List<Vehicle?>?{
     return   repository.getVehicle(attributeId,3,vehicleId,attributeValueId)
    }

}