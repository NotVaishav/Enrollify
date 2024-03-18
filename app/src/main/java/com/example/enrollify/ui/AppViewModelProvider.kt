package com.example.enrollify.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.enrollify.EnrollifyApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            EnrollifyViewModel(enrollifyApplication().container.courseRepository)
        }
    }
}

fun CreationExtras.enrollifyApplication(): EnrollifyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EnrollifyApplication)