package com.application.absensi_app.ui.screen.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.application.absensi_app.R
import com.application.absensi_app.util.Action
import com.application.absensi_app.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToAbsensiScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllAbsensi()
        sharedViewModel.readSortState()
    }
    val action by sharedViewModel.action

    val allAbsen by sharedViewModel.allAbsensi.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()

    val scaffoldState = rememberScaffoldState()
    ShowSnackBar(
        scaffoldState = scaffoldState,
        action = action,
        handleDatabaseAction = {
            sharedViewModel.handleDatabaseAction(action = action)
        },
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        title = setMessageSnackBar(
            title = sharedViewModel.title.value,
            action = action
        ),
        actionLabel = setLabelSnackBar(action = action)
    )

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            ListContent(
                onItemClicked = navigateToAbsensiScreen,
                absens = allAbsen,
                sortState = sortState,
            )
        },
        floatingActionButton = {
            ListFab(
                onFabClicked = {
                    navigateToAbsensiScreen(it)
                },
            )
        }
    )
}

@Composable
fun ShowSnackBar(
    scaffoldState: ScaffoldState,
    action: Action,
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    title: String,
    actionLabel: String
) {
    val scope = rememberCoroutineScope()
    handleDatabaseAction()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = title,
                    duration = SnackbarDuration.Short,
                    actionLabel = actionLabel
                )
                undoDeletedAbsensi(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

@Composable
private fun setLabelSnackBar(action: Action) =
    if (action == Action.DELETE) stringResource(R.string.undo) else stringResource(id = R.string.ok)

@Composable
private fun setMessageSnackBar(action: Action, title: String) =
    if (action == Action.DELETE) stringResource(R.string.deleteAbsensi) else "${action.name} : $title"

private fun undoDeletedAbsensi(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed &&
        action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}


