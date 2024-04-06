package com.example.enrollify.ui.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollifyTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navController: NavController,
    canGoBack: Boolean = false
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (canGoBack) {
                navController.popBackStack()
            }
        }
    )
}