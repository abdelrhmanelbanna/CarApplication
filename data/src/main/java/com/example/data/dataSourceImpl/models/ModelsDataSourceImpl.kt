package com.example.data.dataSourceImpl.models

import com.example.data.api.WebServices
import com.example.data.dataSource.ModelsDataSource
import com.example.data.model.models.ModelsDto
import com.example.domain.model.Models
import javax.inject.Inject

class ModelsDataSourceImpl @Inject
    constructor(
        val webServices: WebServices
    ) : ModelsDataSource {

    override suspend fun getModels(brandId: Int): List<Models?>? {

        val result = webServices.getModels(brandId)

        return result.data?.map {
            it?.toModels()
        }
    }
}