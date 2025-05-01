package com.example.domain.usecases

import com.example.domain.model.Models
import com.example.domain.repository.ModelsRepository
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
  private val repository: ModelsRepository
   ) {

    suspend operator fun invoke(brandId: Int, page: Int = 1, pageSize: Int = 20): List<Models> {
        return repository.getModels(brandId, page, pageSize)
    }

}