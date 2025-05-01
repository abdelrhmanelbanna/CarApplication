package com.example.data.dataSource

import com.example.domain.model.Brand

interface BrandDataSource {

   suspend fun getBrands(category:Int = 1):List<Brand>?
}