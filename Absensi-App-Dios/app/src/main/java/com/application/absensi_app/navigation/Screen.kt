package com.application.absensi_app.navigation

import androidx.navigation.NavHostController
import com.application.absensi_app.util.Action
import com.application.absensi_app.util.LIST_SCREEN
import com.application.absensi_app.util.SPLASH_SCREEN

class Screen(private val navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate("list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val list: (Int) -> Unit = { absenId ->
        navController.navigate("absen/$absenId")
    }

    val absen: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

}