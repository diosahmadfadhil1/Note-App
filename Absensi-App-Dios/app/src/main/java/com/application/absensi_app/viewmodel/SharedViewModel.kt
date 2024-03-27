package com.application.absensi_app.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.absensi_app.data.model.Priority
import com.application.absensi_app.data.model.Absensi
import com.application.absensi_app.data.repo.PriorityRepository
import com.application.absensi_app.data.repo.AbsensiRepository
import com.application.absensi_app.util.Action
import com.application.absensi_app.util.MAX_LENGTH_TITLE
import com.application.absensi_app.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val absensiRepository: AbsensiRepository,
    private val priorityRepository: PriorityRepository
) : ViewModel() {


    private val _allAbsensi = MutableStateFlow<RequestState<List<Absensi>>>(RequestState.Idle)
    val allAbsensi = _allAbsensi.asStateFlow()
    private val _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState = _sortState.asStateFlow()

    var desc: MutableState<String> = mutableStateOf("")
    var title: MutableState<String> = mutableStateOf("")
    var subject: MutableState<String> = mutableStateOf("")
    var priority: MutableState<Priority> = mutableStateOf(Priority.LOW)
    var id: MutableState<Int> = mutableStateOf(0)
    var action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)


    fun getAllAbsensi() {
        _allAbsensi.value = RequestState.Loading
        try {
            viewModelScope.launch {
                absensiRepository.getAllAbsensi.collect {
                    _allAbsensi.value = RequestState.Success(it)
                }
            }
        } catch (ex: Exception) {
            _allAbsensi.value = RequestState.Error(ex)
        }
    }

    private val _selectedAbsensi: MutableStateFlow<Absensi?> = MutableStateFlow(null)
    val selectedAbsensi: StateFlow<Absensi?> = _selectedAbsensi

    fun getSelectedAbsensi(absenId: Int) {
        viewModelScope.launch {
            absensiRepository.getSelectedAbsensi(absenId = absenId).collect {
                _selectedAbsensi.value = it
            }
        }
    }

    fun handleDatabaseAction(action: Action) {
        Log.d("action -> $action ", "action -> $action ")
        when (action) {
            Action.ADD -> addAbsensi()
            Action.DELETE -> deleteAbsensi()
            Action.UPDATE -> updateAbsensi()
            Action.UNDO -> addAbsensi(id.value)
            else -> {}
        }
        if (this.action.value != Action.NO_ACTION)
            this.action.value = Action.NO_ACTION
    }

    private fun updateAbsensi() {
        viewModelScope.launch(Dispatchers.IO) {
            absensiRepository.updateAbsensi(
                Absensi(
                    id = id.value,
                    title = title.value,
                    desc = desc.value,
                    subject = subject.value,
                    priority = priority.value,
                )
            )
        }
    }

    private fun deleteAbsensi() {
        viewModelScope.launch(Dispatchers.IO) {
            absensiRepository.deleteAbsensi(
                Absensi(
                    id = id.value,
                    title = title.value,
                    desc = desc.value,
                    subject = subject.value,
                    priority = priority.value,
                )
            )
        }
    }

    private fun addAbsensi() {
        viewModelScope.launch(Dispatchers.IO) {
            absensiRepository.addAbsensi(
                Absensi(
                    title = title.value,
                    desc = desc.value,
                    subject = subject.value,
                    priority = priority.value
                )
            )
        }
    }

    private fun addAbsensi(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            absensiRepository.addAbsensi(
                Absensi(
                    id = id,
                    title = title.value,
                    desc = desc.value,
                    subject = subject.value,
                    priority = priority.value
                )
            )
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length <= MAX_LENGTH_TITLE)
            title.value = newTitle
    }

    fun validateField(): Boolean {
        return desc.value.isNotEmpty() && title.value.isNotEmpty()
    }

    fun updateContentAbsensi(absensi: Absensi?) {
        if (absensi != null) {
            desc.value = absensi.desc
            subject.value = absensi.subject
            title.value = absensi.title
            id.value = absensi.id
            priority.value = absensi.priority
        } else {
            desc.value = ""
            subject.value = ""
            id.value = 0
            title.value = ""
            priority.value = Priority.LOW
        }
    }

    fun readSortState() {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                priorityRepository.readSortState
                    .map { Priority.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        } catch (ex: Exception) {
            _sortState.value = RequestState.Error(ex)
        }
    }
}