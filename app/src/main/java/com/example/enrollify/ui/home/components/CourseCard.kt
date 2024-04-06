package com.example.enrollify.ui.home.components

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
fun CourseCard(
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
                Row(
                    modifier = modifier.padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Column(modifier = modifier) {
                        Text(text = "Dr. Milton King")

                        Spacer(modifier = modifier.size(5.dp))
                        Text(text = "3 Credits", fontSize = 14.sp)
                    }
                    Spacer(modifier.width(70.dp))
                    Column(modifier = modifier) {
                        when (course.term) {
                            0 -> Text("Term 1 & 2")
                            1 -> Text("Term 1")
                            else -> Text("Term 2")
                        }
                    }
                }
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )

        }

    }
}