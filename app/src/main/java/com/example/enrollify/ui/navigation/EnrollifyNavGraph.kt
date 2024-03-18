package com.example.enrollify.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.courseinfo.CourseInfo
import com.example.enrollify.ui.home.HomeScreen
import com.example.enrollify.ui.mycourses.MyCoursesScreen

enum class EnrollifyNavDestinations(val title: String, val route: String) {
    Home(title = "home", route = "homeScreen"),
    MyCourses(title = "courses", route = "coursesScreen"),
    CourseInfo(title = "courseInfo", route = "courseInfo")
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun EnrollifyNavGraph(
    navController: NavHostController,
//    enrollifyViewModel: EnrollifyViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = EnrollifyNavDestinations.Home.title
    ) {
        composable(route = EnrollifyNavDestinations.Home.title) {
            HomeScreen()
        }
        composable(route = EnrollifyNavDestinations.CourseInfo.title) {
            CourseInfo()
        }
        composable(route = EnrollifyNavDestinations.MyCourses.title) {
            MyCoursesScreen()
        }
    }
}