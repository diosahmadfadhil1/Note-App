package com.application.absensi_app.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.application.absensi_app.ui.screen.absen.AbsensiScreen
import com.application.absensi_app.util.Action
import com.application.absensi_app.util.ABSENSI_ARGUMENT_KEY
import com.application.absensi_app.util.ABSENSI_SCREEN
import com.application.absensi_app.viewmodel.SharedViewModel

@ExperimentalAnimationApi
fun NavGraphBuilder.absensiComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = ABSENSI_SCREEN, arguments = listOf(
            navArgument(
                name = ABSENSI_ARGUMENT_KEY
            ) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val absenId = navBackStackEntry.arguments!!
            .getInt(ABSENSI_ARGUMENT_KEY)
        LaunchedEffect(key1 = absenId) {
            sharedViewModel.getSelectedAbsensi(absenId = absenId)
        }
        val selectedAbsensi by sharedViewModel.selectedAbsensi.collectAsState()
        LaunchedEffect(key1 = selectedAbsensi) {
            if ((selectedAbsensi != null || absenId == -1))
                sharedViewModel.updateContentAbsensi(selectedAbsensi)
        }
        AbsensiScreen(
            selectedAbsensi = selectedAbsensi,
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel
        )
    }
}