package com.application.absensi_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absensi")
data class Absensi(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val desc: String,
    val priority: Priority,
    val subject: String
)