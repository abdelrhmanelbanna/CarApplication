package com.example.data.Repository.brand

import com.example.data.dataSource.BrandDataSource
import com.example.domain.model.Brand
import com.example.domain.repository.BrandsRepository
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
  val dataSource: BrandDataSource
) : BrandsRepository {

    override suspend fun getBrands(category: Int): List<Brand?>? {
       val response =  dataSource.getBrands(category)
        return response
    }
}