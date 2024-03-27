package com.application.absensi_app.ui.screen.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.application.absensi_app.R
import com.application.absensi_app.ui.theme.fabBackgroundColor


@Composable
fun ListFab(
    onFabClicked: (Int) -> Unit,
) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.fabBackgroundColor,
        onClick = {
            onFabClicked(-1)
        }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                R.string.add_absensi
            ),
            tint = Color.White
        )
    }
}