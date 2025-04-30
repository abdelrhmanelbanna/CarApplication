package com.example.data.Repository.vehicle

import com.example.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract  class Di {

    @Binds
   abstract fun provideVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ):VehicleRepository
}