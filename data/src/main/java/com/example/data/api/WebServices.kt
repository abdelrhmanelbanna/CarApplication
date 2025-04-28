package com.example.data.api

import com.example.data.model.brand.BrandResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("brands")
    suspend fun getBrands(@Query("category") category:Int =3):BrandResponse

}