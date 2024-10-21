package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import android.util.Half
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.base.component.BigButton
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp


@Composable
fun AddManualFaultDialog(
    state: AddManualFaultDialogState,
    onEvent: (AddManualFaultDialogEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onEvent(AddManualFaultDialogEvent.OnClickOutsideDialog) },
        contentAlignment = Center
    ) {
        Column {
            Card(
                Modifier
                    .fillMaxSize(0.8f)
                    // To override the clickable area of the outer box
                    .clickable(enabled = false) { },
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(DefaultDp)
                ) {
                    Text(
                        stringResource(R.string.title_adding_manual_errors),
                        Modifier.align(CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
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
                    Spacer(Modifier.height(DefaultDp))
                    LazyColumn(
                        Modifier.weight(1f),
                    ) {
                        items(state.faultGroups) {
                            FaultGroupItem(
                                faultGroup = it,
                                onAddFaultInPlaceClicked = { faultPlaceType, fault ->
                                    onEvent(
                                        AddManualFaultDialogEvent.OnToggleAddFaultToPlace(
                                            fault,
                                            faultPlaceType
                                        )
                                    )
                                })
                        }
                    }
                }
            }
            BigButton(
                onClick = { onEvent(AddManualFaultDialogEvent.OnConfirmAddedFaults) },
                Modifier
                    .align(End)
                    .padding(HalfDefaultDp),
            ) {
                Text(stringResource(R.string.label_confirm))
            }
        }
    }
}

@PreviewTablet
@Composable
private fun AddManualFaultDialogPreview() {
    AppTheme {
        AddManualFaultDialog(
            state = AddManualFaultDialogState(
                faultGroups = listOf(
                    FaultGroup.ForPreview,
                    FaultGroup.ForPreview,
                    FaultGroup.ForPreview
                )
            ),
            onEvent = {},
            modifier = Modifier
        )
    }
}