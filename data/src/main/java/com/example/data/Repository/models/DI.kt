package com.example.data.Repository.models

import com.example.domain.repository.ModelsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun provideModelsRepository(
        modelsRepositoryImpl: ModelsRepositoryImpl
    ):ModelsRepository

}