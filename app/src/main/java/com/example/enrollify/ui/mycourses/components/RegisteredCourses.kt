package com.example.enrollify.ui.mycourses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enrollify.data.EnrollifyUIState
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch


@Composable
fun RegisteredCourses(
    modifier: Modifier = Modifier,
    enrollifyUIState: EnrollifyUIState,
    navController: NavController,
    enrollifyViewModel: EnrollifyViewModel
) {
    val scope = rememberCoroutineScope()
    if (enrollifyUIState.coursesList.none { it.isRegistered }) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No Registered Courses!")
        }
    } else {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 15.dp)
        ) {
            items(items = enrollifyUIState.coursesList.filter { it.isRegistered }) { item ->
                DoneCourseCard(navController = navController, course = item,
                    onBtnClick = {
                        scope.launch {
                            enrollifyViewModel.getCoursePrereq(item.id)
                            enrollifyViewModel.getDependentCourses(item)
                            navController.navigate("${EnrollifyNavDestinations.CourseInfo.route}/register")
                        }
                    }
                )
                Spacer(modifier = modifier.size(15.dp))
            }
        }
    }
}