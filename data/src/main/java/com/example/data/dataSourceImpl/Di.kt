package com.example.data.dataSourceImpl

import com.example.data.dataSource.BrandDataSource
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

}