package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp


@Composable
fun AddManualFaultDialog(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        Card(modifier.fillMaxSize(0.8f)) {
            Column(Modifier.fillMaxSize()) {
                Text(
                    stringResource(R.string.title_adding_manual_errors),
                    Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.height(HalfDefaultDp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.text_error_type),
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        stringResource(R.string.text_start_up),
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        stringResource(R.string.text_round_about_and_intersection),
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        stringResource(R.string.text_movement_and_parking),
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                LazyColumn(Modifier.weight(1f)) {
                    items(10) {
                        FaultGroupItem(FaultGroup.ForPreview)
                    }
                }
            }

        }
    }
}

@PreviewTablet
@Composable
private fun AddManualFaultDialogPreview() {
    AddManualFaultDialog()
}