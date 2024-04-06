package com.example.enrollify.ui.mycourses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.common.EnrollifyBottomAppBar
import com.example.enrollify.ui.common.EnrollifyTopAppBar
import com.example.enrollify.ui.courseinfo.AboutSection
import com.example.enrollify.ui.courseinfo.PrerequisiteSection
import com.example.enrollify.ui.courseinfo.TermSection
import com.example.enrollify.ui.home.components.CourseCard
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
        bottomBar = { EnrollifyBottomAppBar(navController = navController) }) { innerPadding ->
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
                    0 -> LazyColumn(
                        modifier = modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        items(items = enrollifyUIState.coursesList.filter { it.isRegistered }) { item ->
                            CourseCard(navController = navController, course = item,
                                onBtnClick = {
                                    scope.launch {
                                        enrollifyViewModel.getCoursePrereq(item.id)
                                        enrollifyViewModel.getDependentCourses(item)
                                        navController.navigate(EnrollifyNavDestinations.CourseInfo.title)
                                    }
                                }
                            )
                            Spacer(modifier = modifier.size(15.dp))
                        }
                    }

                    1 -> LazyColumn(
                        modifier = modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        items(items = enrollifyUIState.coursesList.filter { it.isCompleted }) { item ->
                            CourseCard(navController = navController, course = item,
                                onBtnClick = {
                                    scope.launch {
                                        enrollifyViewModel.getCoursePrereq(item.id)
                                        navController.navigate(EnrollifyNavDestinations.CourseInfo.title)
                                    }
                                }
                            )
                            Spacer(modifier = modifier.size(15.dp))
                        }
                    }
                }
            }
        }


    }

}