package com.example.enrollify.ui.courseinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch

@Composable
fun CourseInfo(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel
) {
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    Column {
        Text("Course Info")
        Text(enrollifyUIState.currentCourse?.id.toString())
        Spacer(modifier = modifier.size(20.dp))
        LazyColumn {
            items(items = enrollifyUIState.prereqList) { item ->
                Row {
                    Text(text = item.id.toString())
                    Spacer(modifier = modifier.size(15.dp))
                    Text(text = item.name)

                }
            }
        }
        Button(onClick = { enrollifyViewModel.checkEligibility(enrollifyUIState.currentCourse!!) }) {

        }
    }
}