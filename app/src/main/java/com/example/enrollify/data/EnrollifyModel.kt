package com.example.enrollify.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "courses", indices = [Index(value = ["courseUniqueId"], unique = true)])
@TypeConverters(CourseListConverter::class)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val courseUniqueId: String,
    val backgroundImage: String? = null,
    val name: String,
    val professor: String,
//    val prereq: List<Course> = listOf(),
    val term: Int,
    val about: String,
    val isCompleted: Boolean = false,
    var isRegistered: Boolean,
    var registeredInTerm: Int = 0
)


@Entity(
    tableName = "prerequisites",
    primaryKeys = ["courseId", "prerequisiteId"]
)
data class Prerequisite(
    val courseId: Int,
    val prerequisiteId: Int
)


//data class CourseWithPrerequisites(
//    @Embedded val course: Course,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "courseId",
//        associateBy = Junction(
//            value = Prerequisite::class,
//            parentColumn = "id",
//            entityColumn = "prerequisiteId"
//        )
//    )
//    val prerequisites: List<Course>
//)
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

