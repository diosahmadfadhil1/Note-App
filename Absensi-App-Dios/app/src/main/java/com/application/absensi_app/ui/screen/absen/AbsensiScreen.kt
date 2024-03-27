package com.application.absensi_app.ui.screen.absen

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.application.absensi_app.data.model.Priority
import com.application.absensi_app.data.model.Absensi
import com.application.absensi_app.util.Action
import com.application.absensi_app.viewmodel.SharedViewModel
import com.application.absensi_app.R


@Composable
fun AbsensiScreen(
    selectedAbsensi: Absensi?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val desc: String by sharedViewModel.desc
    val title: String by sharedViewModel.title
    val priority: Priority by sharedViewModel.priority
    val subject: String by sharedViewModel.subject
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AbsensiAppBar(
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION)
                        navigateToListScreen(action)
                    else {
                        if (sharedViewModel.validateField())
                            navigateToListScreen(action)
                        else showToast(context)
                    }
                },
                selectedAbsensi = selectedAbsensi
            )
        },
        content = {
            AbsensiContent(
                title = title,
                onTitleChanged = { newTitle ->
                   sharedViewModel.updateTitle(newTitle)
                },
                subject = subject,
                onSubjectChanged = { newSubject ->
                    sharedViewModel.subject.value = newSubject
                },
                description = desc,
                onDescChanged = { newDesc ->
                    sharedViewModel.desc.value = newDesc
                },
                priority = priority,
                onPriorityChanged = { newPriority ->
                    sharedViewModel.priority.value = newPriority
                }
            )
        }
    )
}

fun showToast(context: Context) {
    Toast.makeText(
        context,
        context.getString(R.string.emptyToast),
        Toast.LENGTH_SHORT
    ).show()
}
