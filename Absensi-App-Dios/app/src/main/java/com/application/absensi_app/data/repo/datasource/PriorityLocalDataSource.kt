package com.application.absensi_app.data.repo.datasource

import com.application.absensi_app.data.model.Priority
import kotlinx.coroutines.flow.Flow

interface PriorityLocalDataSource {
    val readSortState: Flow<String>

    suspend fun editSortState(priority: Priority)
}