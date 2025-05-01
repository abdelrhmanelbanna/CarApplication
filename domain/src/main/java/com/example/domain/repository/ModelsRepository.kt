package com.example.domain.repository

import com.example.domain.model.Models

interface ModelsRepository {

    suspend fun getModels(brandId: Int, page: Int, pageSize: Int): List<Models>

}