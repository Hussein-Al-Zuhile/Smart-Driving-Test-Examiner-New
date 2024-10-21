package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.domain.model.Fault
import com.tatweer.smartdrivingtest.domain.model.FaultPlaceType

@Composable
fun FaultAddingItem(
    fault: Fault,
    onFaultAddedInPlace: (FaultPlaceType, Fault) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            fault.name,
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
        FaultPlaceType.entries.forEach { faultPlaceType ->
            Checkbox(
                faultPlaceType in fault.addedInPlaces,
                onCheckedChange = { onFaultAddedInPlace(faultPlaceType, fault) },
                Modifier.weight(1f),
                enabled = faultPlaceType in fault.availablePlaces,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFaultAddingItem() {
    FaultAddingItem(Fault.ForPreview, { _, _ -> })
}