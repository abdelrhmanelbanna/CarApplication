package com.example.data.Repository

import com.example.data.Repository.brand.BrandRepositoryImpl
import com.example.data.Repository.models.ModelsRepositoryImpl
import com.example.data.Repository.vehicle.VehicleRepositoryImpl
import com.example.domain.repository.BrandsRepository
import com.example.domain.repository.ModelsRepository
import com.example.domain.repository.VehicleRepository
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

    @Binds
    abstract fun provideModelsRepository(
        modelsRepositoryImpl: ModelsRepositoryImpl
    ): ModelsRepository

    @Binds
    abstract fun provideVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository
}