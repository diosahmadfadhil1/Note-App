package com.application.absensi_app.data.database

import androidx.room.*
import com.application.absensi_app.data.model.Absensi
import kotlinx.coroutines.flow.Flow

@Dao
interface AbsensiDao {
    @Query("SELECT * FROM absensi ORDER BY id ASC")
    fun getAllAbsensi(): Flow<List<Absensi>>

    @Query("SELECT * FROM absensi WHERE id=:taskId ")
    fun getSelectedAbsensi(taskId: Int): Flow<Absensi>

    @Delete
    suspend fun deleteAbsensi(absensi: Absensi)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbsensi(absensi: Absensi)

    @Update
    suspend fun updateAbsensi(absensi: Absensi)


}