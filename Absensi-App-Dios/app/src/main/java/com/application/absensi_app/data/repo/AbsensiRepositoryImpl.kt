package com.application.absensi_app.data.repo

import com.application.absensi_app.data.model.Absensi
import com.application.absensi_app.data.repo.datasource.AbsensiLocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AbsensiRepositoryImpl @Inject constructor(
    private val localDataSource: AbsensiLocalDataSource
) : AbsensiRepository {

    override val getAllAbsensi: Flow<List<Absensi>>
        get() = localDataSource.getAllAbsensi

    override fun getSelectedAbsensi(absenId: Int): Flow<Absensi> {
        return localDataSource.getSelectedAbsensi(absenId)
    }

    override suspend fun addAbsensi(absen: Absensi) {
        return localDataSource.addAbsensi(absen)
    }

    override suspend fun deleteAbsensi(absen: Absensi) {
        return localDataSource.deleteAbsensi(absen)
    }

    override suspend fun updateAbsensi(absen: Absensi) {
        return localDataSource.updateAbsensi(absen)
    }
}