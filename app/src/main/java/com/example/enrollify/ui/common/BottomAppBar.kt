package com.example.enrollify.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations


sealed class Screen(val route: String, val routeList: Array<String>, val icon: ImageVector) {
    data object Home : Screen(
        EnrollifyNavDestinations.Home.title,
        arrayOf(
            EnrollifyNavDestinations.Home.title,
            EnrollifyNavDestinations.CourseInfo.title
        ),
        Icons.Filled.Home
    )

    data object MyCourses :
        Screen(
            EnrollifyNavDestinations.MyCourses.title,
            arrayOf(
                EnrollifyNavDestinations.MyCourses.title,

                ),
            Icons.Filled.AccountCircle
        )
}

val items = listOf(
    Screen.Home,
    Screen.MyCourses,
)

@Composable
fun EnrollifyBottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    enrollifyViewModel: EnrollifyViewModel
) {
    NavigationBar(
//        containerColor = Color(0xFFF5F5F5),
        modifier = modifier
            .height(78.dp)
            .fillMaxSize()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentSize()
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute in item.routeList,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        enrollifyViewModel.resetCourseValues()
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = null)
                    },
//                    colors = NavigationBarItemDefaults
//                        .colors(
//                            selectedIconColor = Color.Blue,
//                            indicatorColor = Color(0xFFF5F5F5),
//                        ),
                    modifier = modifier.wrapContentSize()
                )
            }
        }
    }
}