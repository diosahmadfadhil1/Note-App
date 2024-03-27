package com.application.absensi_app.data.model

import androidx.compose.ui.graphics.Color
import com.application.absensi_app.ui.theme.HighPriorityColor
import com.application.absensi_app.ui.theme.LowPriorityColor
import com.application.absensi_app.ui.theme.MediumPriorityColor
import com.application.absensi_app.ui.theme.NonePriorityColor

enum class Priority(var color: Color) {
    LOW(LowPriorityColor),
    MEDIUM(MediumPriorityColor),
    HIGH(HighPriorityColor),
    NONE(NonePriorityColor)
}