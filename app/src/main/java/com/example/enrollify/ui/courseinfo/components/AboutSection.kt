package com.example.enrollify.ui.courseinfo.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.enrollify.data.Course

@Composable
fun AboutSection(modifier: Modifier = Modifier, course: Course) {
    Text(
        text = course.about
    )
}
