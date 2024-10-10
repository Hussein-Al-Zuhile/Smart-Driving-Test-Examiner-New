package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun EmergencyStopItem(
    emergencyStopType: EmergencyStopType,
    selected: Boolean,
    onItemClick: (EmergencyStopType) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedButton(
        { onItemClick(emergencyStopType) },
        modifier,
        colors = if (selected) ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) else ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(Modifier.padding(DefaultDp).width(IntrinsicSize.Max), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(emergencyStopType.icon, contentDescription = null,Modifier.fillMaxWidth(0.3f).aspectRatio(2f))
            Spacer(Modifier.height(HalfDefaultDp))
            Text(
                stringResource(emergencyStopType.nameRes),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}

@Preview
@Composable
private fun EmergencyStopPreview() {
    AppTheme {
        Column {
            EmergencyStopItem(EmergencyStopType.Medical, true, {})
            EmergencyStopItem(EmergencyStopType.Medical, false, {})
        }
    }
}