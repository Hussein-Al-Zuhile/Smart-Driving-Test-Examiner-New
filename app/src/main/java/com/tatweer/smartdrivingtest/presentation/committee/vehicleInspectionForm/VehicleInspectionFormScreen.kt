package com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDefaultDp

@Composable
fun VehicleInspectionFormScreen(
    state: VehicleInspectionFormScreenState,
    onEvent: (VehicleInspectionFormScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(DoubleDefaultDp)
    ) {
        Text(
            stringResource(R.string.text_vehicle_inspection_form),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontStyle = MaterialTheme.typography.titleLarge.fontStyle
        )
        Spacer(Modifier.height(DefaultDp))

        Text(
            stringResource(R.string.text_commission) + " #",
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
        )
        OutlinedCard {
            Text(
                text = state.vehicleInspectionForm.license.toString(),
                Modifier.padding(vertical = DefaultDp, horizontal = DoubleDefaultDp)
            )
        }
        Spacer(Modifier.height(DefaultDp))
        Text(
            stringResource(R.string.text_license) + " #",
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedCard {
                Text(
                    text = state.vehicleInspectionForm.commission.toString(),
                    Modifier.padding(vertical = DefaultDp, horizontal = DoubleDefaultDp)
                )
            }
            Spacer(Modifier.weight(1f))
            var isMechanicalError by remember { mutableStateOf(false) }
            Text(
                "Car Mechanical Error",
                Modifier.clickable { isMechanicalError = !isMechanicalError })
            Spacer(Modifier.width(HalfDefaultDp))
            Switch(isMechanicalError, { isMechanicalError = it })
            Spacer(Modifier.weight(1f))
            var unClean by remember { mutableStateOf(false) }
            Text("Car Mechanical Error", Modifier.clickable { unClean = !unClean })
            Spacer(Modifier.width(HalfDefaultDp))
            Switch(unClean, { unClean = it })
        }
        Spacer(Modifier.height(DefaultDp))
        OutlinedTextField(
            value = state.vehicleInspectionForm.vehicleNotes,
            { onEvent(VehicleInspectionFormScreenEvent.OnVehicleNotesChanged(it)) },
            Modifier.fillMaxWidth(),
            minLines = 3,
            label = { Text(stringResource(R.string.label_vehicle_notes)) })
        Spacer(Modifier.height(DefaultDp))

        Row {
            OutlinedTextField(
                value = state.vehicleInspectionForm.systemNotes,
                { onEvent(VehicleInspectionFormScreenEvent.OnSystemNotesChanged(it)) },
                Modifier.weight(1f),
                minLines = 3,
                label = { Text(stringResource(R.string.label_system_notes)) })
            Spacer(Modifier.width(DefaultDp))
            OutlinedTextField(
                value = state.vehicleInspectionForm.systemNotesDescription,
                {
                    onEvent(
                        VehicleInspectionFormScreenEvent.OnSystemNotesDescriptionChanged(
                            it
                        )
                    )
                },
                Modifier.weight(1f),
                minLines = 3,
                label = { Text(stringResource(R.string.label_system_notes_description)) })
        }
        Spacer(Modifier.height(DefaultDp))
        Spacer(Modifier.weight(1f))

        Row {
            OutlinedButton(onClick = { onEvent(VehicleInspectionFormScreenEvent.OnCancelClicked) }) {
                Text(
                    stringResource(R.string.label_cancel),
                    Modifier.padding(ThreeQuarteredDefaultDp)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(onClick = { onEvent(VehicleInspectionFormScreenEvent.OnSendClicked) }) {
                Text(stringResource(R.string.label_send), Modifier.padding(ThreeQuarteredDefaultDp))
            }
        }

    }
}

@PreviewTablet
@Composable
private fun VehicleInspectionFormScreenPreview() {
    AppTheme {
        Surface {
            VehicleInspectionFormScreen(
                state = VehicleInspectionFormScreenState(),
                onEvent = {}
            )
        }
    }
}