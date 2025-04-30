package com.example.data.dataSource

import com.example.domain.model.Vehicle

interface VehicleDataSource {

    suspend fun getVehicle(
        attributeId: Int,
        category: Int = 3,
        vehicleId: Int,
        attributeValueId: Int
    ):List<Vehicle?>?
}