package com.application.absensi_app.ui.screen.absen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.application.absensi_app.component.PriorityDropDownItem
import com.application.absensi_app.data.model.Priority
import com.application.absensi_app.ui.theme.LARGE_PADDING
import com.application.absensi_app.ui.theme.MEDIUM_PADDING
import com.application.absensi_app.ui.theme.SMALL_PADDING
import com.application.absensi_app.R

@Composable
fun AbsensiContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    subject: String,
    onSubjectChanged: (String) -> Unit,
    description: String,
    onDescChanged: (String) -> Unit,
    priority: Priority,
    onPriorityChanged: (Priority) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            singleLine = true,
            onValueChange = onTitleChanged,
            textStyle = MaterialTheme.typography.body1,
            label = {
                Text(text = stringResource(id = R.string.name))
            },
        )
        Spacer(
            modifier = Modifier.padding(top = MEDIUM_PADDING)
        )
        PriorityDropDownItem(
            priority = priority,
            onPriorityChanged = onPriorityChanged
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SMALL_PADDING),
            value = subject,
            onValueChange = onSubjectChanged,
            textStyle = MaterialTheme.typography.body1,
            label = {
                Text(text = stringResource(id = R.string.subject))
            }
        )
        Spacer(
            modifier = Modifier.padding(top = MEDIUM_PADDING)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize()
                .padding(top = SMALL_PADDING),
            value = description,
            onValueChange = onDescChanged,
            textStyle = MaterialTheme.typography.body1,
            label = {
                Text(text = stringResource(id = R.string.desc))
            }
        )

    }
}

@Composable
@Preview
fun Preview() {
    AbsensiContent(
        title = "Hojat",
        onTitleChanged = {},
        description = " TEstTEstTEstTEstTEstTEstTEst",
        subject = "halo",
        onSubjectChanged = {},
        onDescChanged = {},
        priority = Priority.MEDIUM,
        onPriorityChanged = {}
    )
}