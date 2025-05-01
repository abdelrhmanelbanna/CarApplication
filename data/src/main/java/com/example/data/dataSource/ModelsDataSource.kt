package com.example.data.dataSource

import com.example.domain.model.Models

interface ModelsDataSource {

    suspend fun getModels(brandId: Int):List<Models?>?

}