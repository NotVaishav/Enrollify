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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnrollifyViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(EnrollifyUIState())
    val uiState: StateFlow<EnrollifyUIState> = _uiState.asStateFlow()

    init {
        getCourses()
    }

    private fun getCourses() {
        _uiState.update { currentState ->
            currentState.copy(isLoading = true)
        }
        viewModelScope.launch {
            courseRepository.getAllCoursesStream().collect { courses ->
                _uiState.update { currentState ->
                    currentState.copy(
                        coursesList = courses,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getCoursePrereq(id: Int) {
        viewModelScope.launch {
            val tempCourse = _uiState.value.coursesList.find { it.id == id }
            if (tempCourse != null) {
                _uiState.update { currentState ->
                    currentState.copy(currentCourse = tempCourse)
                }
            }

            courseRepository.getPrereqForCourse(id).collect { courses ->
                _uiState.update { currentState ->
                    currentState.copy(prereqList = courses)
                }
                Log.d("tempList", _uiState.value.prereqList.toString())
            }
        }
    }

    fun checkEligibility(course: Course) {
        val courseTerm = course.term

        viewModelScope.launch {
            courseRepository.getAllCoursesStream().collect { courses ->
                val courseCount =
                    courses.count { (it.term == courseTerm || it.term == 0) && it.isRegistered }
                Log.d("TAGHERE", courseCount.toString())
                if (courseCount == 4) {
                    Log.d("COURSELIMIT", "Reached course Limit for the term")
                }

                val allCompletedAndRegistered = _uiState.value.prereqList.all { course ->
                    course.isRegistered || course.isCompleted
                }
                Log.d("COMPLETED", allCompletedAndRegistered.toString())

                if (!allCompletedAndRegistered) {
                    Log.d("COURSENOTRC", "Prereq courses are not registered or completed")
                }
            }

        }
    }

    fun updateCourse(course: Course) {
        course.isRegistered = !course.isRegistered
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }
}