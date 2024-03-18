package com.example.enrollify.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    LazyColumn {
        items(items = enrollifyUIState.coursesList) { item ->
            Text(text = item.name)
            Spacer(modifier = modifier.size(15.dp))
        }
    }

}