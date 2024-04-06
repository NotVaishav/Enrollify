package com.example.enrollify.ui.courseinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.enrollify.R
import com.example.enrollify.data.Course
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseInfo(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    navController: NavController
) {
    val titles = listOf("About", "Term", "Prerequisites")
    var tabIndex by remember { mutableStateOf(0) }
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier.fillMaxSize()) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(top = 0.dp)
        ) {
            Box(modifier = modifier.height(230.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.test_image),
                    contentDescription = null,
                    modifier = modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = modifier.align(Alignment.TopStart)) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.9f),

                            )
                    }
                }
                Box(
                    modifier = modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Advanced Database", modifier = modifier
                            .background(
                                Color.DarkGray.copy(alpha = 0.5f),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp),
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
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
                    .fillMaxHeight()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = modifier

                        .weight(1f, false)
                ) {
                    when (tabIndex) {
                        0 -> AboutSection()
                        1 -> TermSection()
                        2 -> PrerequisiteSection(prereqList = enrollifyUIState.prereqList)
                    }
                }
                ElevatedButton(
                    onClick = {
                        enrollifyViewModel.setBottomSheetValue(true)
                        enrollifyViewModel.checkEligibility(enrollifyUIState.currentCourse!!)
                    },
                    modifier = modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                ) {
                    Text(
                        text = "Check Eligibility",
                        modifier = modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp
                    )
                }
                if (enrollifyUIState.showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            enrollifyViewModel.setBottomSheetValue(false)
                            enrollifyViewModel.resetCourseValues()
                        },
                        sheetState = sheetState,
                        modifier = modifier.height(400.dp),
                        dragHandle = {}
                    ) {
                        var finalTerm = 0

                        Column {
                            Button(
                                onClick = {
                                    scope.launch {
                                        enrollifyViewModel.unRegisterCourse(enrollifyUIState.currentCourse!!)
                                        enrollifyViewModel.setBottomSheetValue(false)
                                        enrollifyViewModel.resetCourseValues()
                                    }
                                },
                            ) {
                                Text("unreg")
                            }
                            if (enrollifyUIState.currentCourse?.isCompleted == true) {
                                Text(text = "Already Completed")
                            } else if (enrollifyUIState.currentCourse?.isRegistered == true) {
                                Text(text = "Already Registered")
                            } else {
                                if (enrollifyUIState.prereqList.isEmpty()) {
                                    Text("Prereqs are not needed")
                                } else {
                                    if (enrollifyUIState.prereqNotCompleted) {
                                        Text("Prereq not completed or registered")
                                    } else {
                                        Text("All Prereq are either completed or registered for")
                                    }
                                }
                                if (!enrollifyUIState.prereqNotCompleted) {
                                    if (enrollifyUIState.currentCourse?.term == 0) {
                                        if (enrollifyUIState.courseCountFirstTermReached && enrollifyUIState.courseCountSecondTermReached) {
                                            Text("Reached Max Capacity")
                                        } else if (enrollifyUIState.courseCountFirstTermReached) {
                                            Text("Can book for second term only")
                                        } else if (enrollifyUIState.courseCountSecondTermReached) {
                                            Text("Can book for first term only")
                                        } else {
                                            if (enrollifyUIState.canOnlyRegisterInSecondTerm) {
                                                Text("Can book for second term only because prereq is currently in term one")
                                                finalTerm = 2
                                            } else {
                                                Text("Can book for any term")
                                                val terms = listOf(1, 2)
                                                var selectedTerm by remember { mutableStateOf(terms[0]) }
                                                Column(Modifier.padding(16.dp)) {
                                                    terms.forEach { term ->
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            modifier = Modifier.padding(vertical = 8.dp)
                                                        ) {
                                                            RadioButton(
                                                                selected = selectedTerm == term,
                                                                onClick = {
                                                                    selectedTerm = term
                                                                    finalTerm = term
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
                                            Text("Max Courses Reached for Term")
                                        } else {
                                            Text("Can register for the term")
                                        }
                                    }
                                }
                                Button(
                                    onClick = {
                                        scope.launch {
                                            enrollifyViewModel.updateCourse(
                                                enrollifyUIState.currentCourse!!,
                                                finalTerm
                                            )
                                            enrollifyViewModel.setBottomSheetValue(false)
                                            enrollifyViewModel.resetCourseValues()
                                        }

                                    },
                                    enabled = !enrollifyUIState.maxCourseReached && !enrollifyUIState.prereqNotCompleted
                                ) {
                                    Text("Check")
                                }

                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    Text(
        text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut laoreet ex, vel elementum odio. In condimentum mi id magna accumsan sagittis. Sed blandit, orci ac interdum pharetra, turpis dolor rutrum massa, eget fringilla urna risus mattis eros. Morbi nec ipsum eu diam dictum hendrerit eu dignissim ipsum. Donec ac enim mollis, tempus mauris at, hendrerit nibh."
    )
}

@Composable
fun TermSection(modifier: Modifier = Modifier) {
    Text(text = "This course is offered only in the 1st term")
}

@Composable
fun PrerequisiteSection(modifier: Modifier = Modifier, prereqList: List<Course>) {
    LazyColumn {
        items(items = prereqList) { item ->
            Row {
                Text(text = item.courseUniqueId)
                Spacer(modifier = modifier.size(15.dp))
                Text(text = item.name)
            }
        }
    }
}