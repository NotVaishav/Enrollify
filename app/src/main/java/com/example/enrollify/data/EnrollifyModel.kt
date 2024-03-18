package com.example.enrollify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "courses")
@TypeConverters(CourseListConverter::class)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val courseUniqueId: String,
    val backgroundImage: String? = null,
    val name: String,
    val professor: String,
    val prereq: List<Course> = listOf(),
    val term: Int,
    val about: String,
    val isCompleted: Boolean = false,
    val isRegistered: Boolean
)

class CourseListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCourseList(courseList: List<Course>): String {
        return gson.toJson(courseList)
    }

    @TypeConverter
    fun toCourseList(courseListString: String): List<Course> {
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.fromJson(courseListString, type)
    }
}

