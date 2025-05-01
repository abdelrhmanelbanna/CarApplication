package com.example.data.dataSourceImpl

import com.example.data.dataSource.BrandDataSource
import com.example.data.dataSource.ModelsDataSource
import com.example.data.dataSource.VehicleDataSource
import com.example.data.dataSourceImpl.brand.BrandDataSourceImpl
import com.example.data.dataSourceImpl.models.ModelsDataSourceImpl
import com.example.data.dataSourceImpl.vehicle.VehicleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
   abstract fun provideBrandDataSource(
        brandDataSourceImpl: BrandDataSourceImpl
    ):BrandDataSource
    @Binds
    abstract fun provideModelsDataSource(
        modelsDataSourceImpl: ModelsDataSourceImpl
    ): ModelsDataSource
    @Binds
    abstract fun provideVehicleDataSource(
        vehicleDataSourceImpl: VehicleDataSourceImpl
    ): VehicleDataSource
}