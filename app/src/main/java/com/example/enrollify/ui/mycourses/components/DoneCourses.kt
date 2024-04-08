package com.example.enrollify.ui.mycourses.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enrollify.data.EnrollifyUIState
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch


@Composable
fun DoneCourses(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    enrollifyUIState: EnrollifyUIState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 15.dp)
    ) {
        items(items = enrollifyUIState.coursesList.filter { it.isCompleted }) { item ->
            DoneCourseCard(navController = navController, course = item,
                onBtnClick = {
                    scope.launch {
                        enrollifyViewModel.getCoursePrereq(item.id)
                        navController.navigate("${EnrollifyNavDestinations.CourseInfo.route}/done")
                    }
                }
            )
            Spacer(modifier = modifier.size(15.dp))
        }
    }
}