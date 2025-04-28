package com.example.data.Repository.brand

import com.example.data.model.brand.BrandResponse
import com.example.domain.repository.BrandsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun provideBrandRepository(
        brandRepositoryImpl: BrandRepositoryImpl
    ):BrandsRepository
}