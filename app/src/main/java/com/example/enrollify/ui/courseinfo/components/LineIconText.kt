package com.example.enrollify.ui.courseinfo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LineIconText(modifier: Modifier = Modifier, iconValue: String = "wrong", givenText: String) {
    Row(modifier = modifier.padding(vertical = 2.dp)) {
        when (iconValue) {
            "wrong" -> Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                tint = Color.Red.copy(alpha = 0.8f)
            )

            "right" -> Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = Color.Green.copy(alpha = 0.8f)
            )

            "info" -> Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.6f)
            )
        }
        Text(
            givenText,
            modifier = modifier.padding(start = 10.dp)
        )
    }
}