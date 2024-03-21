package com.example.enrollify.data

data class EnrollifyUIState(
    val coursesList: List<Course> = listOf(),
    val prereqList: List<Course> = listOf(),
    var isLoading: Boolean = false,
    var isEligible: Boolean = false,
    var currentCourse: Course? = null
)
