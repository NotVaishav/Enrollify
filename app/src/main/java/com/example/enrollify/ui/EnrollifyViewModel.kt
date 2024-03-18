package com.example.enrollify.ui

import androidx.lifecycle.ViewModel
import com.example.enrollify.data.EnrollifyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EnrollifyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EnrollifyUIState())
    val uiState: StateFlow<EnrollifyUIState> = _uiState.asStateFlow()
}