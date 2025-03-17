package com.example.testapp.utils

import androidx.compose.ui.graphics.Color
import com.example.testapp.R
import com.example.testapp.ui.theme.Orange
import com.example.testapp.ui.theme.Pink

enum class Mode {
    NONE, EDIT, DELETE;

    fun modeColor(): Color {
        return when (this) {
            NONE -> Pink
            EDIT -> Orange
            DELETE -> Color.Red
        }
    }
    fun iconColor(): Int {
        return when (this) {
            NONE -> R.drawable.ic_phone
            EDIT -> R.drawable.ic_edit
            DELETE -> R.drawable.ic_delete
        }
    }
}