package com.example.data.dataSourceImpl.vehicle

import com.example.data.dataSource.VehicleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {


    @Binds
   abstract fun provideVehicleDataSource(
       vehicleDataSourceImpl: VehicleDataSourceImpl
   ):VehicleDataSource
}