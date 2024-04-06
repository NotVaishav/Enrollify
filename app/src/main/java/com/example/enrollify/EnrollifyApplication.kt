package com.example.enrollify

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.enrollify.data.Course
import com.example.enrollify.data.EnrollifyAppContainer
import com.example.enrollify.data.EnrollifyAppDataContainer
import com.example.enrollify.data.EnrollifyDatabase
import com.example.enrollify.data.Prerequisite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class EnrollifyApplication : Application() {

    lateinit var container: EnrollifyAppContainer

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {

        super.onCreate()
        container = EnrollifyAppDataContainer(this)


//        var coursesExist = false
        val db = Room.databaseBuilder(
            applicationContext,
            EnrollifyDatabase::class.java, "enrollify_database"
        ).build()


        generateFakeData(db)


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun generateFakeData(db: EnrollifyDatabase) {
        GlobalScope.launch {


            val courseDataList = listOf(
                Course(
                    courseUniqueId = "CS128",
                    name = "Introduction to Coding",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS135",
                    name = "Computer Application Technology",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS161",
                    name = "Introduction to Programming",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = true,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS162",
                    name = "Programming and Data Structure",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = true,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS215",
                    name = "Social Issues",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS225",
                    name = "Health Analytics",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS223",
                    name = "Data Science",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS255",
                    name = "Advanced Data Structure",
                    professor = "",
                    term = 0,
                    about = "",
                    isCompleted = true,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS263",
                    name = "Computer Architecture and Organization",
                    professor = "",
                    term = 0,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS275",
                    name = "Database Management Systems",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS277",
                    name = "Discrete Structure",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS340",
                    name = "Evolutionary Computation",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS355",
                    name = "Algorithm Design and Analysis",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS356",
                    name = "Theory of Computing",
                    professor = "",
                    term = 0,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS345",
                    name = "Computer Graphics",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS364",
                    name = "Mobile App Development",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS368",
                    name = "Data Communications and Networking",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS375",
                    name = "Operating Systems",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS361",
                    name = "Natural Language Processing",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS467",
                    name = "Cyber Security",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS485",
                    name = "Software Design",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS435",
                    name = "Algorithms and Complexity",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS444",
                    name = "Machine Learning",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "CS495",
                    name = "Artificial Intelligence",
                    professor = "",
                    term = 2,
                    about = "",
                    isCompleted = false,
                    isRegistered = false
                ),
                Course(
                    courseUniqueId = "Math101",
                    name = "Introduction to Maths",
                    professor = "",
                    term = 1,
                    about = "",
                    isCompleted = true,
                    isRegistered = false
                )
            )
            courseDataList.forEach { course ->
                try {
                    db.courseDao().insert(course)
                } catch (e: Exception) {
                    Log.e("Database", "Error inserting initial data: ${e.message}")
                }
            }
            val course101 = db.courseDao().getCourseFromUnique("Math101").firstOrNull()
            val course162 = db.courseDao().getCourseFromUnique("CS162").firstOrNull()
            val course161 = db.courseDao().getCourseFromUnique("CS161").firstOrNull()
            val course225 = db.courseDao().getCourseFromUnique("CS225").firstOrNull()
            val course223 = db.courseDao().getCourseFromUnique("CS223").firstOrNull()
            val course255 = db.courseDao().getCourseFromUnique("CS255").firstOrNull()
            val course263 = db.courseDao().getCourseFromUnique("CS263").firstOrNull()
            val course275 = db.courseDao().getCourseFromUnique("CS275").firstOrNull()
            val course277 = db.courseDao().getCourseFromUnique("CS277").firstOrNull()
            val course355 = db.courseDao().getCourseFromUnique("CS355").firstOrNull()
            val course356 = db.courseDao().getCourseFromUnique("CS356").firstOrNull()
            val course345 = db.courseDao().getCourseFromUnique("CS345").firstOrNull()
            val course364 = db.courseDao().getCourseFromUnique("CS364").firstOrNull()
            val course368 = db.courseDao().getCourseFromUnique("CS368").firstOrNull()
            val course375 = db.courseDao().getCourseFromUnique("CS375").firstOrNull()
            val course361 = db.courseDao().getCourseFromUnique("CS361").firstOrNull()
            val course467 = db.courseDao().getCourseFromUnique("CS467").firstOrNull()
            val course485 = db.courseDao().getCourseFromUnique("CS485").firstOrNull()
            val course435 = db.courseDao().getCourseFromUnique("CS435").firstOrNull()
            val course444 = db.courseDao().getCourseFromUnique("CS444").firstOrNull()
            val course495 = db.courseDao().getCourseFromUnique("CS495").firstOrNull()





            if (course162 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course162.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course225 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course225.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course223 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course223.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course255 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course255.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course263 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course263.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course275 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course275.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course277 != null && course101 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course277.id,
                        prerequisiteId = course101.id
                    )
                )
            }

            if (course355 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course355.id,
                        prerequisiteId = course277.id
                    )
                )
            }

            if (course355 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course355.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course356 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course356.id,
                        prerequisiteId = course277.id
                    )
                )
            }

            if (course356 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course356.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course345 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course345.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course364 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course364.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course368 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course368.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course375 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course375.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course361 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course361.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course467 != null && course368 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course467.id,
                        prerequisiteId = course368.id
                    )
                )
            }

            if (course485 != null && course162 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course485.id,
                        prerequisiteId = course162.id
                    )
                )
            }

            if (course435 != null && course355 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course435.id,
                        prerequisiteId = course355.id
                    )
                )
            }

            if (course444 != null && course161 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course444.id,
                        prerequisiteId = course161.id
                    )
                )
            }

            if (course495 != null && course255 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course255.id
                    )
                )
            }

            if (course495 != null && course263 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course263.id
                    )
                )
            }

            if (course495 != null && course277 != null) {
                db.courseDao().insertPrerequisite(
                    Prerequisite(
                        courseId = course495.id,
                        prerequisiteId = course277.id
                    )
                )
            }
        }
    }
}


