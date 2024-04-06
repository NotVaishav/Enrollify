package com.example.enrollify.data

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCoursesStream(): Flow<List<Course>>
    fun getCourseStream(id: Int): Flow<Course?>

    fun getCourseFromUnique(id: String): Flow<Course?>

    fun getPrereqForCourse(id: Int): Flow<List<Course>>

    fun getCoursesFromPrereq(id: Int): Flow<List<Course>>
    suspend fun insertCourse(item: Course): Result<Unit>
    suspend fun deleteCourse(item: Course)
    suspend fun updateCourse(item: Course)
    suspend fun getUnregisteredAndUncompletedCoursesStream(): Flow<List<Course>>

    //    suspend fun getCoursesWithPrerequisites(): Flow<List<CourseWithPrerequisites>>
    suspend fun insertPrerequisite(item: Prerequisite): Result<Unit>
}


class OfflineCourseRepository(private val courseDao: CourseDao) : CourseRepository {
    override fun getAllCoursesStream(): Flow<List<Course>> {

        return courseDao.getAllCourses()
    }

    override fun getCourseStream(id: Int): Flow<Course?> = courseDao.getCourse(id)
    override fun getCourseFromUnique(id: String): Flow<Course?> = courseDao.getCourseFromUnique(id)

    override fun getPrereqForCourse(id: Int): Flow<List<Course>> =
        courseDao.getPrereqForCourse(id)

    override fun getCoursesFromPrereq(id: Int): Flow<List<Course>> =
        courseDao.getCoursesFromPrereq(id)

    override suspend fun insertCourse(item: Course): Result<Unit> {
        return try {
            courseDao.insert(item)
            Result.success(Unit)
        } catch (e: SQLiteConstraintException) {
            // Handle the constraint violation error here
            // You can choose to log the error, show a message to the user, or handle it as needed
            Result.failure(e)
        }
    }

    override suspend fun deleteCourse(item: Course) = courseDao.delete(item)
    override suspend fun updateCourse(item: Course) = courseDao.update(item)
    override suspend fun getUnregisteredAndUncompletedCoursesStream(): Flow<List<Course>> =
        courseDao.getUnregisteredAndUncompletedCourses()

//    override suspend fun getCoursesWithPrerequisites(): Flow<List<CourseWithPrerequisites>> =
//        courseDao.getCoursesWithPrerequisites()

    override suspend fun insertPrerequisite(item: Prerequisite): Result<Unit> {
        return try {
            courseDao.insertPrerequisite(item)
            Result.success(Unit)
        } catch (e: SQLiteConstraintException) {
            // Handle the constraint violation error here
            // You can choose to log the error, show a message to the user, or handle it as needed
            Result.failure(e)
        }
    }
}
