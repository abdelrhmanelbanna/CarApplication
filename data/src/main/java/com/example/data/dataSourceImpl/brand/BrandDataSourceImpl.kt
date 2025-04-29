package com.example.data.dataSourceImpl.brand

import com.example.data.api.WebServices
import com.example.data.dataSource.BrandDataSource
import com.example.domain.model.Brand
import javax.inject.Inject

class BrandDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : BrandDataSource{

    override suspend fun getBrands(category: Int): List<Brand?>? {

        try {
            val response = webServices.getBrands(category)
            return  response.data?.map {
                it?.toBrand()
            }
        }
        catch (ex:Exception){
            throw ex
        }

    }
}