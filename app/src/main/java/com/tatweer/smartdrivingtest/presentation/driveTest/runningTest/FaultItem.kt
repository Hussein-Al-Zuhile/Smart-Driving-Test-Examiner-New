package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme

@Composable
fun FaultItem(
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                "Fault headline",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        },
        supportingContent = {
            Column {
                Text("Fault supporting content")
                Text("25-04-2024 14:20", color = MaterialTheme.colorScheme.error)
            }
        },
        trailingContent = {
            var isRejected by remember { mutableStateOf(false) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Checkbox(checked = isRejected, onCheckedChange = { isRejected = it })
                Text(
                    if (isRejected) "Rejected" else "Reject",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        modifier = modifier.clip(MaterialTheme.shapes.medium)
    )
}

@Preview
@Composable
private fun PreviewFaultItem() {
    AppTheme {
        FaultItem()
    }
}