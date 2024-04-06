package com.example.enrollify.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Course): Long

    @Update
    suspend fun update(item: Course)

    @Delete
    suspend fun delete(item: Course)

    @Query("SELECT * from courses WHERE id = :id")
    fun getCourse(id: Int): Flow<Course>

    @Query("SELECT * from courses WHERE courseUniqueId = :id")
    fun getCourseFromUnique(id: String): Flow<Course>

    @Query("SELECT * from courses ORDER BY name ASC")
    fun getAllCourses(): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE id IN (SELECT prerequisiteId FROM prerequisites WHERE courseId = :id)")
    fun getPrereqForCourse(id: Int): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE id IN (SELECT courseId FROM prerequisites WHERE prerequisiteId = :id)")
    fun getCoursesFromPrereq(id: Int): Flow<List<Course>>

    @Query(
        "SELECT * FROM courses WHERE isRegistered = 1 AND isCompleted = 1 ORDER BY name ASC"
    )
    fun getUnregisteredAndUncompletedCourses(): Flow<List<Course>>

//    @Transaction
//    @Query("SELECT * FROM courses")
//    fun getCoursesWithPrerequisites(): Flow<List<CourseWithPrerequisites>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPrerequisite(item: Prerequisite): Long
}