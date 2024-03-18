package com.example.enrollify.data

import android.content.Context

interface EnrollifyAppContainer {
    val courseRepository: CourseRepository
}

class EnrollifyAppDataContainer(private val context: Context) : EnrollifyAppContainer {

    override val courseRepository: CourseRepository by lazy {
        OfflineCourseRepository(EnrollifyDatabase.getDatabase(context).courseDao())
    }
}