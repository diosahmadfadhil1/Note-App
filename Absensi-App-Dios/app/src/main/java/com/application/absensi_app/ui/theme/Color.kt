package com.application.absensi_app.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

//color Priority absensi
val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFFFFFFFF)

//colors TopAppBar
val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray
val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.Black

//background splash
val Colors.splashBackground: Color
    @Composable
    get() = if (isLight) Purple700 else Color.Black

//color fab
val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) Teal200 else Purple700

//color absenItem
val Colors.absensiItemBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else DarkGray
val Colors.absensiItemTextColor: Color
    @Composable
    get() = if (isLight) DarkGray else LightGray
