package com.example.enrollify.ui.mycourses.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.enrollify.data.Course

@Composable
fun DoneCourseCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    course: Course,
    onBtnClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onBtnClick()
            },
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = modifier.weight(0.8f)) {
                Row(modifier = modifier) {
                    Text(
                        text = "${course.courseUniqueId} : ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = course.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                if (course.isCompleted) {
                    if (course.term == 0) {
                        Text(text = "Term 1 & 2")
                    } else {
                        Text(text = "Term ${course.term}")
                    }
                } else {
                    Text(text = "Term ${course.registeredInTerm}")
                }

            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )

        }

    }
}