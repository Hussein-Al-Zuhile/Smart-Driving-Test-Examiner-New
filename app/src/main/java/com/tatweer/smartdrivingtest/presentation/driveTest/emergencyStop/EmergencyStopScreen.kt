package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun EmergencyStopScreen(
    state: EmergencyStopScreenState,
    onEvent: (EmergencyStopScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            stringResource(R.string.text_select_emergency_stop_reason),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    horizontal = DefaultDp,
                    vertical = DefaultDp
                )
                .align(CenterHorizontally),
        )
        Spacer(Modifier.weight(1f))
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EmergencyStopType.entries.forEach {
                EmergencyStopItem(
                    it,
                    state.selectedEmergencyStopType == it,
                    onItemClick = {
                        onEvent(EmergencyStopScreenEvent.OnEmergencyStopSelected(it))
                    },
                    Modifier.fillMaxHeight()
                )
            }
        }

        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { onEvent(EmergencyStopScreenEvent.OnCancelClicked) },
                Modifier
                    .padding(DoubleDefaultDp),
            ) {
                Text(stringResource(R.string.label_cancel), Modifier.padding(HalfDefaultDp))
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onEvent(EmergencyStopScreenEvent.OnConfirmClicked) },
                Modifier
                    .padding(DoubleDefaultDp),
                enabled = state.selectedEmergencyStopType != null
            ) {
                Text(stringResource(R.string.label_confirm), Modifier.padding(HalfDefaultDp))
            }
        }
    }
}

@PreviewTablet
@Composable
private fun RouteSelectionScreenPreview() {
    AppTheme {
        EmergencyStopScreen(EmergencyStopScreenState(), {})
    }
}