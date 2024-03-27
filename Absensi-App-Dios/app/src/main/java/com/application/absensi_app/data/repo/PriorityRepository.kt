package com.application.absensi_app.data.repo

import com.application.absensi_app.data.model.Priority
import kotlinx.coroutines.flow.Flow

interface PriorityRepository {
    val readSortState: Flow<String>

    suspend fun editSortState(priority: Priority)
}