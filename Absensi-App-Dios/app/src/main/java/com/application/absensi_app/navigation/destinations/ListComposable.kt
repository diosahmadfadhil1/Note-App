package com.application.absensi_app.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.application.absensi_app.ui.screen.list.ListScreen
import com.application.absensi_app.util.LIST_ARGUMENT_KEY
import com.application.absensi_app.util.LIST_SCREEN
import com.application.absensi_app.util.toAction
import com.application.absensi_app.viewmodel.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN, arguments = listOf(
            navArgument(
                name = LIST_ARGUMENT_KEY
            ) {
                type = NavType.StringType
            }
        )
    ) { navBackStack ->
        val action = navBackStack.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        ListScreen(
            navigateToAbsensiScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel,
        )
    }
}