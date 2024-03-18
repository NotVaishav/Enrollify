package com.example.enrollify.data

import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCoursesStream(): Flow<List<Course>>
    fun getCourseStream(id: Int): Flow<Course?>
    suspend fun insertCourse(item: Course)
    suspend fun deleteCourse(item: Course)
    suspend fun updateCourse(item: Course)
    suspend fun getUnregisteredAndUncompletedCoursesStream(): Flow<List<Course>>
}


class OfflineCourseRepository(private val courseDao: CourseDao) : CourseRepository {
    override fun getAllCoursesStream(): Flow<List<Course>> = courseDao.getAllCourses()
    override fun getCourseStream(id: Int): Flow<Course?> = courseDao.getCourse(id)
    override suspend fun insertCourse(item: Course) = courseDao.insert(item)
    override suspend fun deleteCourse(item: Course) = courseDao.delete(item)
    override suspend fun updateCourse(item: Course) = courseDao.update(item)
    override suspend fun getUnregisteredAndUncompletedCoursesStream(): Flow<List<Course>> =
        courseDao.getUnregisteredAndUncompletedCourses()
}