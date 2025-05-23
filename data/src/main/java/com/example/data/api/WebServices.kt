package com.example.data.api

import com.example.data.model.brand.BrandResponse
import com.example.data.model.models.ModelsResponse
import com.example.data.model.vehicle.VehicleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("brands")
    suspend fun getBrands(@Query("category") category:Int =3):BrandResponse


    @GET("models")
    suspend fun getModels(
        @Query("brand") brandId: Int,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("category") category: Int = 3
    ): ModelsResponse

    @GET("vehicles")
    suspend fun getVehicles(
        @Query("identification_attribute_id") attributeId: Int,
        @Query("category") category: Int = 3,
        @Query("vehicle_id") vehicleId: Int ,
        @Query("identification_attribute_value_id") attributeValueId: Int,
    ):VehicleResponse



}