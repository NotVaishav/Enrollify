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
                if (courseTerm == 0) {
                    val courseCountFirstTerm =
                        courses.count { it.isRegistered && (it.term == 1 || (it.term == 0 && it.registeredInTerm == 1)) }
                    val courseCountSecondTerm =
                        courses.count { it.isRegistered && (it.term == 2 || (it.term == 0 && it.registeredInTerm == 2)) }
                    if (courseCountFirstTerm == 4) {
                        _uiState.update { currentState ->
                            currentState.copy(courseCountFirstTermReached = true)
                        }
                    }
                    if (courseCountSecondTerm == 4) {
                        _uiState.update { currentState ->
                            currentState.copy(courseCountSecondTermReached = true)
                        }
                    }
                } else {
                    val courseCount =
                        courses.count { (it.term == courseTerm || it.term == 0) && it.isRegistered }
                    Log.d("TAGHERE", courseCount.toString())
                    if (courseCount == 4) {
                        _uiState.update { currentState ->
                            currentState.copy(maxCourseReached = true)
                        }
                        Log.d("COURSELIMIT", "Reached course Limit for the term")
                    }
                }
                val allCompletedAndRegistered = _uiState.value.prereqList.all { course ->
                    if (course.term == 0) {
                        _uiState.update { currentState ->
                            currentState.copy(canOnlyRegisterInSecondTerm = true)
                        }
                        (course.isRegistered && course.registeredInTerm == 1) || course.isCompleted
                    } else {
                        course.isRegistered || course.isCompleted
                    }
                }
                Log.d("COMPLETED", allCompletedAndRegistered.toString())

                if (!allCompletedAndRegistered) {
                    _uiState.update { currentState ->
                        currentState.copy(prereqNotCompleted = true)
                    }
                    Log.d("COURSENOTRC", "Prereq courses are not registered or completed")
                }
            }
        }
    }

    fun updateCourse(course: Course, term: Int = 0) {
        course.isRegistered = !course.isRegistered
        course.registeredInTerm = term
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }

    fun unregisterDatabaseCourse(course: Course, term: Int = 0, boolVal: Boolean = false) {
        course.isRegistered = boolVal
        course.registeredInTerm = term
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }

    fun getDependentCourses(course: Course) {
        viewModelScope.launch {
            courseRepository.getCoursesFromPrereq(course.id).collect { courses ->
                _uiState.update { currentState ->
                    Log.d("Checking", courses.toString())
                    currentState.copy(
                        dependentCourses = courses
                    )
                }
            }
        }
    }

    fun unRegisterCourse(course: Course) {
        viewModelScope.launch {
            _uiState.value.dependentCourses.forEach {
                if (!it.isCompleted) {
                    unregisterDatabaseCourse(it)
                }
            }
            unregisterDatabaseCourse(course)

        }
    }


    fun setBottomSheetValue(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showBottomSheet = value)
        }
    }

    fun resetCourseValues() {
        _uiState.update { currentState ->
            currentState.copy(
                prereqNotCompleted = false,
                maxCourseReached = false,
                courseCountFirstTermReached = false,
                courseCountSecondTermReached = false,
                canOnlyRegisterInSecondTerm = false
            )
        }
    }
}