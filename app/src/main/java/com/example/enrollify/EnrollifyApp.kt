package com.example.enrollify

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavGraph


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun EnrollifyScreen(navController: NavHostController = rememberNavController()) {
    EnrollifyNavGraph(navController = navController)
}