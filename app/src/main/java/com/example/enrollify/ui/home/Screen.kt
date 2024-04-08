package com.example.enrollify.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.common.EnrollifyBottomAppBar
import com.example.enrollify.ui.common.EnrollifyTopAppBar
import com.example.enrollify.ui.home.components.CourseCard
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    navController: NavController,
) {
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { EnrollifyTopAppBar(title = "Enrollify", navController = navController) },
        bottomBar = {
            EnrollifyBottomAppBar(
                navController = navController,
                enrollifyViewModel = enrollifyViewModel
            )
        }) { innerPadding ->

        if (enrollifyUIState.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(horizontal = 15.dp)
            ) {
                items(items = enrollifyUIState.coursesList.filter { !it.isRegistered && !it.isCompleted }) { item ->
                    CourseCard(navController = navController, course = item,
                        onBtnClick = {
                            scope.launch {
                                enrollifyViewModel.getCoursePrereq(item.id)
                                enrollifyViewModel.getDependentCourses(item)
                                navController.navigate("${EnrollifyNavDestinations.CourseInfo.route}/regular")

                            }
                        }
                    )
                    Spacer(modifier = modifier.size(15.dp))
                }
            }
        }
    }
}