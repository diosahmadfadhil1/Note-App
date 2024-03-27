package com.application.absensi_app.ui.screen.absen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.application.absensi_app.R
import com.application.absensi_app.component.DisplayAlertDialog
import com.application.absensi_app.data.model.Priority
import com.application.absensi_app.data.model.Absensi
import com.application.absensi_app.ui.theme.topAppBarBackgroundColor
import com.application.absensi_app.ui.theme.topAppBarContentColor
import com.application.absensi_app.util.Action

@Composable
fun AbsensiAppBar(
    selectedAbsensi: Absensi?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedAbsensi == null)
        NewAbsensiAppBar(navigateToListScreen = navigateToListScreen)
    else ExistingAbsensiAppBar(
        selectedAbsensi = selectedAbsensi,
        navigateToListScreen = navigateToListScreen
    )
}

@Composable
fun NewAbsensiAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        navigationIcon = {
            BackIconAction(
                onBackClicked = navigateToListScreen
            )
        },
        title = {
            Text(
                text = stringResource(R.string.add_absensi),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            AddIconAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingAbsensiAppBar(
    selectedAbsensi: Absensi,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        navigationIcon = {
            CloseIconAction(
                onCloseClicked = navigateToListScreen
            )
        },
        title = {
            Text(
                text = selectedAbsensi.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ExistingAbsensiAppBarActions(
                selectedAbsensi = selectedAbsensi,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}

@Composable
fun ExistingAbsensiAppBarActions(
    selectedAbsensi: Absensi,
    navigateToListScreen: (Action) -> Unit
) {
    var openDialog: Boolean by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(
            id = R.string.deleteAbsensi,
            selectedAbsensi.title
        ),
        text = stringResource(
            id = R.string.deleteAbsensiDescription,
            selectedAbsensi.title
        ),
        confirmClick = { navigateToListScreen(Action.DELETE) },
        closeClick = { openDialog = false },
        openDialog = openDialog
    )
    DeleteIconAction(
        onDeleteClicked = {
            openDialog = true
        },
    )
    UpdateIconAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun AddIconAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onAddClicked(Action.ADD)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(
                R.string.add_absensi
            ), tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun BackIconAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onBackClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(
                R.string.back_action
            ), tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun CloseIconAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onCloseClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(
                R.string.close_action
            ), tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateIconAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onUpdateClicked(Action.UPDATE)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(
                R.string.close_action
            ), tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteIconAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onDeleteClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(
                R.string.delete_action
            ), tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun NewAbsensiAppBarPreview(
) {
    NewAbsensiAppBar(
        navigateToListScreen = { }
    )
}

@Composable
@Preview
fun ExistingAbsensiAppBarPreview(
) {
    ExistingAbsensiAppBar(selectedAbsensi =
    Absensi(
        id = 0,
        title = "Hojat",
        desc = "descdescdescdescdescdescdesc",
        priority = Priority.HIGH,
        subject = ""
    ),
        navigateToListScreen = { }
    )
}


