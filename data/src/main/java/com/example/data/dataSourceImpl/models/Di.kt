package com.example.data.dataSourceImpl.models


import com.example.data.dataSource.ModelsDataSource
import com.example.domain.repository.ModelsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun provideModelsDataSource(
        modelsDataSourceImpl: ModelsDataSourceImpl
    ): ModelsDataSource

}