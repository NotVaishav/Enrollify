package com.example.enrollify.ui.mycourses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enrollify.data.EnrollifyUIState
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.common.EnrollifyBottomAppBar
import com.example.enrollify.ui.common.EnrollifyTopAppBar

import com.example.enrollify.ui.home.components.CourseCard
import com.example.enrollify.ui.mycourses.components.DoneCourseCard
import com.example.enrollify.ui.mycourses.components.DoneCourses
import com.example.enrollify.ui.mycourses.components.RegisteredCourses
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch

@Composable
fun MyCoursesScreen(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    navController: NavController
) {
    val titles = listOf("Registered", "Completed")
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { EnrollifyTopAppBar(title = "My Courses", navController = navController) },
        bottomBar = {
            EnrollifyBottomAppBar(
                navController = navController,
                enrollifyViewModel = enrollifyViewModel
            )
        }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            Column(
                modifier = modifier
//                    .fillMaxHeight()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
//                verticalArrangement = Arrangement.SpaceBetween
            ) {
                when (tabIndex) {
                    0 -> RegisteredCourses(
                        enrollifyUIState = enrollifyUIState,
                        navController = navController,
                        enrollifyViewModel = enrollifyViewModel
                    )

                    1 -> DoneCourses(
                        enrollifyViewModel = enrollifyViewModel,
                        enrollifyUIState = enrollifyUIState,
                        navController = navController
                    )
                }
            }
        }
    }
}


