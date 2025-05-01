package com.example.data.Repository.models

import com.example.data.dataSource.ModelsDataSource
import com.example.domain.model.Models
import com.example.domain.repository.ModelsRepository
import javax.inject.Inject

class ModelsRepositoryImpl @Inject constructor
    (val dataSource: ModelsDataSource) : ModelsRepository{

    override suspend fun getModels(brandId: Int, page: Int, pageSize: Int): List<Models> {
        val result = dataSource.getModels(brandId, page, pageSize)
        return result!!
    }
}