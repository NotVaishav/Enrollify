package com.example.enrollify.ui.courseinfo.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.enrollify.data.EnrollifyUIState
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch


@Composable
fun RegisterSection(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    enrollifyUIState: EnrollifyUIState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(top = 30.dp, start = 15.dp, end = 15.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = modifier.padding(bottom = 20.dp)) {
            if (enrollifyUIState.currentCourse?.isCompleted == true) {
                Text(text = "Already Completed")
            } else if (enrollifyUIState.currentCourse?.isRegistered == true) {
                Text(text = "Already Registered")
            } else {
                if (enrollifyUIState.prereqList.isEmpty()) {
                    LineIconText(givenText = "Prerequisites are not needed", iconValue = "right")
                } else {
                    if (enrollifyUIState.prereqNotCompleted) {
                        LineIconText(
                            givenText = "Prerequisites not completed or registered",
                            iconValue = "wrong"
                        )

                    } else {
                        LineIconText(
                            givenText = "All Prerequisites are either completed or registered.",
                            iconValue = "right"
                        )

                    }
                }
                if (!enrollifyUIState.prereqNotCompleted) {
                    if (enrollifyUIState.currentCourse?.term == 0) {
                        if (enrollifyUIState.courseCountFirstTermReached && enrollifyUIState.courseCountSecondTermReached) {
                            LineIconText(givenText = "Reached Max Capacity", iconValue = "wrong")
                        } else if (enrollifyUIState.courseCountFirstTermReached) {
                            LineIconText(
                                givenText = "Can book for second term only",
                                iconValue = "info"
                            )
                            enrollifyViewModel.setFinalTermValue(2)
                        } else if (enrollifyUIState.courseCountSecondTermReached) {
                            LineIconText(
                                givenText = "Can book for first term only",
                                iconValue = "info"
                            )
                            enrollifyViewModel.setFinalTermValue(1)

                        } else {
                            if (enrollifyUIState.canOnlyRegisterInSecondTerm) {
                                LineIconText(
                                    givenText = "Can book for second term only because prerequisite is currently in term one",
                                    iconValue = "info"
                                )
                                enrollifyViewModel.setFinalTermValue(2)
                            } else {
                                LineIconText(
                                    givenText = "Can book for any term",
                                    iconValue = "right"
                                )
                                val terms = listOf(1, 2)
                                var selectedTerm by remember { mutableStateOf(terms[0]) }

                                Column(Modifier.padding(vertical = 16.dp)) {
                                    Text(text = "Select Term:")
                                    terms.forEach { term ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,

                                            ) {
                                            RadioButton(
                                                selected = selectedTerm == term,
                                                onClick = {
                                                    selectedTerm = term
                                                    enrollifyViewModel.setFinalTermValue(term)
                                                },
                                                modifier = Modifier.padding(end = 8.dp)
                                            )
                                            Text(text = "Term $term")
                                        }
                                    }
                                }
                            }

                        }
                    } else {
                        if (enrollifyUIState.maxCourseReached) {
                            LineIconText(
                                givenText = "Max Courses Reached for Term",
                                iconValue = "wrong"
                            )
                        } else {
                            LineIconText(
                                givenText = "Can register for the term",
                                iconValue = "right"
                            )
                            enrollifyViewModel.setFinalTermValue(enrollifyUIState.currentCourse?.term!!)

                        }
                    }
                }
            }


        }
        Button(
            onClick = {
                scope.launch {
                    enrollifyViewModel.updateCourse(
                        enrollifyUIState.currentCourse!!,
                        enrollifyUIState.finalTermValue
                    )
                    enrollifyViewModel.getCourses()
                    enrollifyViewModel.setBottomSheetValue(false)
                    enrollifyViewModel.resetCourseValues()
                    navController.navigate(EnrollifyNavDestinations.MyCourses.title)
                    Toast.makeText(
                        context,
                        "Course registered successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            },
            modifier = modifier.fillMaxWidth(),
            enabled = !enrollifyUIState.maxCourseReached && !enrollifyUIState.prereqNotCompleted
        ) {
            Text("Register")
        }

    }
}