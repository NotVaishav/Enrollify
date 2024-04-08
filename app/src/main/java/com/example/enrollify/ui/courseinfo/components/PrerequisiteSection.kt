package com.example.enrollify.ui.courseinfo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enrollify.data.Course


@Composable
fun PrerequisiteSection(modifier: Modifier = Modifier, prereqList: List<Course>) {
    Column {
        if (prereqList.isEmpty()) {
            Text("This course doesn't have any prerequisites")
        } else {
            Text("The following courses are required as prerequisites:")
            Spacer(modifier = modifier.size(15.dp))
            LazyColumn {
                items(items = prereqList) { item ->
                    Row {
                        Text(text = item.courseUniqueId + " : ")
                        Spacer(modifier = modifier.size(15.dp))
                        Text(text = item.name)
                    }
                }
            }
        }
    }
}
