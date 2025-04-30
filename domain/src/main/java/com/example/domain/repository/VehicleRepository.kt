package com.example.domain.repository

import com.example.domain.model.Vehicle

interface VehicleRepository {

    suspend fun getVehicle(
        attributeId: Int,
        category: Int = 3,
        vehicleId: Int,
        attributeValueId: Int
    ):List<Vehicle?>?
}