package com.example.enrollify.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel
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
    if (enrollifyUIState.isLoading) {
        Text(text = "Loading")
    } else {
        LazyColumn {
            items(items = enrollifyUIState.coursesList) { item ->
                Text(text = item.name)
                Spacer(modifier = modifier.size(15.dp))
                Button(onClick = {
                    scope.launch {
                        enrollifyViewModel.getCoursePrereq(item.id)
                        navController.navigate(EnrollifyNavDestinations.CourseInfo.title)
                    }
                }
                ) {
                    Text(text = "GET")
                }
            }
        }
    }


}