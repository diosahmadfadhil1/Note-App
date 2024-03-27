package com.application.absensi_app.data.repo.datasource

import com.application.absensi_app.data.database.AbsensiDao
import com.application.absensi_app.data.model.Absensi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AbsensiLocalDataSourceImpl @Inject constructor(
    private val absensiDao: AbsensiDao
) : AbsensiLocalDataSource {

    override val getAllAbsensi: Flow<List<Absensi>>
        get() = absensiDao.getAllAbsensi()
    override fun getSelectedAbsensi(absenId: Int): Flow<Absensi> {
        return absensiDao.getSelectedAbsensi(absenId)
    }

    override suspend fun addAbsensi(absen: Absensi) {
        return absensiDao.insertAbsensi(absen)
    }

    override suspend fun deleteAbsensi(absen: Absensi) {
        return absensiDao.deleteAbsensi(absen)
    }

    override suspend fun updateAbsensi(absen: Absensi) {
        return absensiDao.updateAbsensi(absen)
    }


}