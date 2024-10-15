package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.DoDisturbAlt
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp

@Composable
fun RunningTestScreen(
    state: RunningTestScreenState,
    onEvent: (RunningTestScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.background(MaterialTheme.colorScheme.surface)) {
        Row(Modifier.weight(1f)) {
            Card(Modifier.fillMaxWidth(0.4f)) {
                LazyColumn(
                    contentPadding = PaddingValues(DefaultDp),
                    verticalArrangement = Arrangement.spacedBy(
                        HalfDefaultDp
                    )
                ) {
                    items(10) {
                        FaultItem()
                    }
                }
            }
            Box(Modifier.weight(1f)) {
                Image(
                    painterResource(R.drawable.united_arab_emirates),
                    contentDescription = "Map",
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                )
                ElevatedCard(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(DefaultDp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row(Modifier.padding(HalfDefaultDp)) {
                        Column(
                            Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(horizontal = QuarterDefaultDp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                state.minutes.toString(),
                                style = MaterialTheme.typography.displaySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                stringResource(R.string.text_min).uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(Modifier.width(DefaultDp))
                        Column(
                            Modifier.background(MaterialTheme.colorScheme.surface),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                state.seconds.toString(),
                                style = MaterialTheme.typography.displaySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                stringResource(R.string.text_second).uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
        Row(Modifier.padding(HalfDefaultDp)) {
            Row(Modifier.fillMaxWidth(0.4f)) {
                Button(
                    onClick = { onEvent(RunningTestScreenEvent.OnEmergencyStop) },
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.DoDisturbAlt, contentDescription = null)
                        Text(stringResource(R.string.label_emergency_stop))
                    }
                }
                Spacer(Modifier.weight(1f))
                OutlinedButton(onClick = { onEvent(RunningTestScreenEvent.OnAddManualFault) }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.AddCircleOutline, contentDescription = null)
                        Text(stringResource(R.string.label_add_manual_fault))
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            if (state.isTestStarted) {
                Button(
                    onClick = { onEvent(RunningTestScreenEvent.OnStopTest) },
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Column(
                        Modifier.padding(horizontal = DefaultDp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Stop, contentDescription = null)
                        Text(stringResource(R.string.label_stop))
                    }
                }
            } else {
                Button(onClick = { onEvent(RunningTestScreenEvent.OnStartTest) }) {
                    Column(
                        Modifier.padding(horizontal = DefaultDp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Text(stringResource(R.string.label_start))
                    }
                }
            }
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewRunningTestScreen() {
    AppTheme {
        RunningTestScreen(RunningTestScreenState(), {})
    }
}