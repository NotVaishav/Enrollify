package com.example.enrollify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Course::class, Prerequisite::class], version = 1, exportSchema = false)
abstract class EnrollifyDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var Instance: EnrollifyDatabase? = null

        fun getDatabase(context: Context): EnrollifyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, EnrollifyDatabase::class.java, "enrollify_database")
                    .build().also { Instance = it }
            }
        }
    }
}