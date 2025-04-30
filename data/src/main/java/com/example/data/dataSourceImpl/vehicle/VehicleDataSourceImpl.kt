package com.example.data.dataSourceImpl.vehicle

import com.example.data.api.WebServices
import com.example.data.dataSource.VehicleDataSource
import com.example.domain.model.Vehicle
import javax.inject.Inject

class VehicleDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : VehicleDataSource {

    override suspend fun getVehicle(
        attributeId: Int,
        category: Int,
        vehicleId: Int,
        attributeValueId: Int
    ): List<Vehicle?>? {
        val result = webServices.getVehicles(attributeId,3,
            vehicleId,attributeValueId)

        return  result.data?.map {
            it?.toVehicle()
        }
    }
}