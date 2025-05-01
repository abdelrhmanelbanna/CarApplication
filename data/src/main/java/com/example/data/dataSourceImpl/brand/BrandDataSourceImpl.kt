package com.example.data.dataSourceImpl.brand

import android.util.Log
import com.example.data.api.WebServices
import com.example.data.dataSource.BrandDataSource
import com.example.domain.model.Brand
import javax.inject.Inject

class BrandDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : BrandDataSource {


    override suspend fun getBrands(category: Int): List<Brand>? {
        Log.d("BrandDataSource", "Fetching brands for category: $category")
        val response = webServices.getBrands(category)
        Log.d("BrandDataSource", "Response: $response, Data: ${response.data}")
        return response.data?.map {
            it.toBrand()
        }.also {
            Log.d("BrandDataSource", "Mapped brands: $it")
        }
    }
}