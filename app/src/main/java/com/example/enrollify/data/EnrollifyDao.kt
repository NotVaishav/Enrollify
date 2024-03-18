package com.example.enrollify.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Course)

    @Update
    suspend fun update(item: Course)

    @Delete
    suspend fun delete(item: Course)

    @Query("SELECT * from courses WHERE id = :id")
    fun getCourse(id: Int): Flow<Course>

    @Query("SELECT * from courses ORDER BY name ASC")
    fun getAllCourses(): Flow<List<Course>>

    @Query(
        "SELECT * FROM courses WHERE isRegistered = 1 AND isCompleted = 1 ORDER BY name ASC"
    )
    fun getUnregisteredAndUncompletedCourses(): Flow<List<Course>>
}