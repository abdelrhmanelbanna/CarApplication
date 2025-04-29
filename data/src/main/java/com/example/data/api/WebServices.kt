package com.example.data.api

import com.example.data.model.brand.BrandResponse
import com.example.data.model.models.ModelsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("brands")
    suspend fun getBrands(@Query("category") category:Int =3):BrandResponse


    @GET("models")
    suspend fun getModels(
        @Query("brand") brandId: Int,
        @Query("page") page: Int = 1,
        @Query("category") category: Int = 3
    ): ModelsResponse

}