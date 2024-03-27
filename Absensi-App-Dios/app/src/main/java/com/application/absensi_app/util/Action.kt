package com.application.absensi_app.util

enum class Action {
    ADD, DELETE, UPDATE, UNDO, NO_ACTION
}

fun String?.toAction(): Action {
    return when {
        this == "DELETE" -> Action.DELETE
        this == "ADD" -> Action.ADD
        this == "UPDATE" -> Action.UPDATE
        this == "UNDO" -> Action.UNDO
        else -> Action.NO_ACTION
    }
}