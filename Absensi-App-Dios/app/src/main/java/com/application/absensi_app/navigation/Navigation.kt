package com.application.absensi_app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.application.absensi_app.navigation.destinations.listComposable
import com.application.absensi_app.navigation.destinations.splashComposable
import com.application.absensi_app.navigation.destinations.absensiComposable
import com.application.absensi_app.util.SPLASH_SCREEN
import com.application.absensi_app.viewmodel.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember {
        Screen(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        absensiComposable(
            navigateToListScreen = screen.absen,
            sharedViewModel = sharedViewModel
        )
    }

}