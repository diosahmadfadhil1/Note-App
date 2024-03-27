package com.application.absensi_app.di

import com.application.absensi_app.data.repo.PriorityRepository
import com.application.absensi_app.data.repo.PriorityRepositoryImpl
import com.application.absensi_app.data.repo.AbsensiRepository
import com.application.absensi_app.data.repo.AbsensiRepositoryImpl
import com.application.absensi_app.data.repo.datasource.PriorityLocalDataSource
import com.application.absensi_app.data.repo.datasource.PriorityLocalDataSourceImpl
import com.application.absensi_app.data.repo.datasource.AbsensiLocalDataSource
import com.application.absensi_app.data.repo.datasource.AbsensiLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BindModule {
    @Binds
    fun provideAbsensiRepositoryImpl(absensiRepositoryImpl: AbsensiRepositoryImpl): AbsensiRepository

    @Binds
    fun provideAbsensiLocalDataSourceImpl(localDataSourceImpl: AbsensiLocalDataSourceImpl): AbsensiLocalDataSource

    @Binds
    fun providePriorityRepositoryImpl(absensiRepositoryImpl: PriorityRepositoryImpl): PriorityRepository

    @Binds
    fun providePriorityLocalDataSourceImpl(localDataSourceImpl: PriorityLocalDataSourceImpl): PriorityLocalDataSource

}