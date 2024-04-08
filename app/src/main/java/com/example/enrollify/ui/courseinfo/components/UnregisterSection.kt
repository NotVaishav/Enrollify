package com.example.enrollify.ui.courseinfo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.enrollify.data.EnrollifyUIState


@Composable
fun UnregisterSection(
    modifier: Modifier = Modifier,
    cancelButton: () -> Unit = {},
    unregisterBtn: () -> Unit = {},
    enrollifyUIState: EnrollifyUIState
) {
    Column(modifier = modifier.padding(top = 40.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        if (enrollifyUIState.dependentCourses.any { it.isRegistered }) {
            Text(text = "The following courses will also be dropped since they require the completion of the current course as a prerequisite:")
            Spacer(modifier = modifier.size(15.dp))
            LazyColumn {
                items(items = enrollifyUIState.dependentCourses.filter { it.isRegistered }) { item ->
                    Row {
                        Text(text = item.courseUniqueId + " : ")
                        Spacer(modifier = modifier.size(15.dp))
                        Text(text = item.name)
                    }
                }
            }
            Spacer(modifier = modifier.size(10.dp))
            Divider(
                modifier = modifier
                    .height(2.dp)
            )
            Spacer(modifier = modifier.size(10.dp))
        }

        Text("Are you sure you want to drop the course?", textAlign = TextAlign.Center)
        Spacer(modifier = modifier.size(50.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = cancelButton, modifier = modifier.size(48.dp)) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = modifier.size(36.dp)
                )
            }
            IconButton(onClick = unregisterBtn) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = modifier.size(36.dp),
                    tint = Color.Red.copy(alpha = 0.8f)
                )
            }
        }
    }
}