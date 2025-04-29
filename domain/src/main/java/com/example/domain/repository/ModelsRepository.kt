package com.example.domain.repository

import com.example.domain.model.Models

interface ModelsRepository {

    suspend fun getModels(brandId: Int): List<Models?>?

}