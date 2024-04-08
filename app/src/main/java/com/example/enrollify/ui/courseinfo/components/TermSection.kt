package com.example.enrollify.ui.courseinfo.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.enrollify.data.Course


@Composable
fun TermSection(modifier: Modifier = Modifier, course: Course) {
    if (course.term == 0) {
        Text(text = "This course is offered in both the terms.")
    } else if (course.term == 1) {
        Text(text = "This course is offered only in the first term.")
    } else if (course.term == 2) {
        Text(text = "This course is offered only in the second term.")
    }
}
