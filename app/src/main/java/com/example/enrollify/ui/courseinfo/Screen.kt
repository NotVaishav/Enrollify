package com.example.enrollify.ui.courseinfo

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.enrollify.R
import com.example.enrollify.data.Course
import com.example.enrollify.data.EnrollifyUIState
import com.example.enrollify.ui.AppViewModelProvider
import com.example.enrollify.ui.EnrollifyViewModel
import com.example.enrollify.ui.courseinfo.components.AboutSection
import com.example.enrollify.ui.courseinfo.components.LineIconText
import com.example.enrollify.ui.courseinfo.components.PrerequisiteSection
import com.example.enrollify.ui.courseinfo.components.RegisterSection
import com.example.enrollify.ui.courseinfo.components.TermSection
import com.example.enrollify.ui.courseinfo.components.UnregisterSection
import com.example.enrollify.ui.navigation.EnrollifyNavDestinations
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseInfo(
    modifier: Modifier = Modifier,
    enrollifyViewModel: EnrollifyViewModel,
    navController: NavController,
    viaUnregister: Boolean = false,
    viaDone: Boolean = false
) {
    val titles = listOf("About", "Term", "Prerequisites")
    var tabIndex by remember { mutableStateOf(0) }
    val enrollifyUIState by enrollifyViewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(modifier = modifier.fillMaxSize()) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(top = 0.dp)
        ) {
            Box(modifier = modifier.height(230.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.itcbg),
                    contentDescription = null,
                    modifier = modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
//                AsyncImage(
//                    model = "https://pub-2e611e3d244a41dba6deb47044c8b9d1.r2.dev/itcbg.jpeg",
//                    contentDescription = null
//                )
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
                        text = enrollifyUIState.currentCourse?.name!!, modifier = modifier
                            .background(
                                Color.Black.copy(alpha = 0.6f),
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
                        0 -> AboutSection(course = enrollifyUIState.currentCourse!!)
                        1 -> TermSection(course = enrollifyUIState.currentCourse!!)
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
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                    enabled = !viaDone
                ) {
                    if (viaDone) {
                        Text("Already Completed")
                    } else {
                        if (viaUnregister) {
                            Text(
                                text = "Unregister",
                                modifier = modifier.padding(vertical = 8.dp),
                                fontSize = 16.sp
                            )
                        } else {
                            Text(
                                text = "Check Eligibility",
                                modifier = modifier.padding(vertical = 8.dp),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (enrollifyUIState.showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            enrollifyViewModel.setBottomSheetValue(false)
                            enrollifyViewModel.resetCourseValues()
                        },
                        sheetState = sheetState,
                        modifier = if (!viaUnregister) {
                            modifier.wrapContentHeight()
                        } else {
                            modifier.wrapContentHeight()
                        },
                        dragHandle = {}
                    ) {
                        Log.d("CHECK", enrollifyUIState.finalTermValue.toString())
                        if (viaUnregister) {
                            UnregisterSection(cancelButton = {
                                enrollifyViewModel.setBottomSheetValue(false)

                            }, unregisterBtn = {
                                scope.launch {
                                    enrollifyViewModel.unRegisterCourse(enrollifyUIState.currentCourse!!)
                                    enrollifyViewModel.setBottomSheetValue(false)
                                    enrollifyViewModel.resetToMainValues()
                                    navController.navigate(EnrollifyNavDestinations.MyCourses.title)
                                    Toast.makeText(
                                        context,
                                        "Course(s) dropped successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }, enrollifyUIState = enrollifyUIState)
                        } else {
                            RegisterSection(
                                enrollifyViewModel = enrollifyViewModel,
                                enrollifyUIState = enrollifyUIState,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}



