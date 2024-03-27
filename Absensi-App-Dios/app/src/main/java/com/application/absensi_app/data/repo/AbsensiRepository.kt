package com.application.absensi_app.data.repo

import com.application.absensi_app.data.model.Absensi
import kotlinx.coroutines.flow.Flow

interface AbsensiRepository {
    val getAllAbsensi: Flow<List<Absensi>>

    fun getSelectedAbsensi(absenId: Int): Flow<Absensi>

    suspend fun addAbsensi(absen: Absensi)

    suspend fun deleteAbsensi(absen: Absensi)

    suspend fun updateAbsensi(absen: Absensi)
}