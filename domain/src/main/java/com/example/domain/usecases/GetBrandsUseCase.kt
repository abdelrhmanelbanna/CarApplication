package com.example.domain.usecases

import com.example.domain.model.Brand
import com.example.domain.repository.BrandsRepository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor (
    private val repository: BrandsRepository
){

    suspend fun invoke():List<Brand>?{
       return repository.getBrands(3)
    }
}