package com.example.enrollify.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enrollify.data.Course
import com.example.enrollify.data.CourseRepository
import com.example.enrollify.data.EnrollifyUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnrollifyViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(EnrollifyUIState())
    val uiState: StateFlow<EnrollifyUIState> = _uiState.asStateFlow()

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            viewModelScope.launch {
                courseRepository.getAllCoursesStream().collect { courses ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            coursesList = courses
                        )
                    }
                }
            }
            Log.d("YAHA", _uiState.value.coursesList.toString())
        }
    }
}