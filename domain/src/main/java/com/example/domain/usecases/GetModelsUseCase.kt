package com.example.domain.usecases

import com.example.domain.model.Brand
import com.example.domain.model.Models
import com.example.domain.repository.ModelsRepository
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
  private val repository: ModelsRepository
   ) {

    suspend fun invoke(brandId:Int):List<Models?>?{
        return repository.getModels(brandId)
    }

}