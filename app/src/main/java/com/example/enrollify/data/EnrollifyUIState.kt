package com.example.enrollify.data

data class EnrollifyUIState(
    val coursesList: List<Course> = listOf(),
    val prereqList: List<Course> = listOf(),
    var isLoading: Boolean = false,
    var isEligible: Boolean = false,
    var showBottomSheet: Boolean = false,
    var currentCourse: Course? = null,
    var prereqNotCompleted: Boolean = false,
    var maxCourseReached: Boolean = false,
    var courseCountFirstTermReached: Boolean = false,
    var courseCountSecondTermReached: Boolean = false,
    var canOnlyRegisterInSecondTerm: Boolean = false,
    var dependentCourses: List<Course> = listOf()
)
