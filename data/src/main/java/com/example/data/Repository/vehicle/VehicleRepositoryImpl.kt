package com.example.data.Repository.vehicle

import com.example.data.dataSource.VehicleDataSource
import com.example.domain.model.Vehicle
import com.example.domain.repository.VehicleRepository
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    val dataSource: VehicleDataSource
):VehicleRepository {

    override suspend fun getVehicle(
        attributeId: Int,
        category: Int,
        vehicleId: Int,
        attributeValueId: Int
    ): List<Vehicle?>? {

        val result = dataSource.getVehicle(attributeId,3,
            vehicleId, attributeValueId)
        return result
    }
}