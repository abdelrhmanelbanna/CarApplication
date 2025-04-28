package com.example.data.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.dataSource.BrandDataSource
import com.example.data.model.brand.BrandDto
import com.example.domain.model.Brand
import javax.inject.Inject

class BrandDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : BrandDataSource{

    override suspend fun getBrands(category: Int): List<Brand?>? {
        val response = webServices.getBrands(category)
        return  response.data?.map {
            it?.toBrand()
        }
    }
}