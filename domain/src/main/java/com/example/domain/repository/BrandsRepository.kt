package com.example.domain.repository

import com.example.domain.model.Brand

interface BrandsRepository {

    suspend fun getBrands(category:Int):List<Brand?>?

}