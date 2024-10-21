package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Fault
import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.domain.model.FaultPlaceType
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun FaultGroupItem(
    faultGroup: FaultGroup,
    onAddFaultInPlaceClicked: (FaultPlaceType, Fault) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    OutlinedCard(modifier.clickable { isExpanded = !isExpanded }) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(HalfDefaultDp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    faultGroup.iconUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
                Spacer(Modifier.width(HalfDefaultDp))
                Text(
                    faultGroup.name,
                    Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton({ isExpanded = !isExpanded }) {
                    Icon(
                        if (isExpanded) Icons.Default.RemoveCircleOutline else Icons.Default.AddCircleOutline,
                        contentDescription = if (isExpanded) {
                            stringResource(R.string.content_description_collapse)
                        } else {
                            stringResource(
                                R.string.content_description_expand
                            )
                        }
                    )
                }
            }
            AnimatedContent(isExpanded, transitionSpec = {
                slideInVertically { -it } togetherWith slideOutVertically()
            }) {
                if (it) {
                    Card(shape = RectangleShape) {
                        Column(Modifier.clickable(enabled = false) { }) {
                            repeat(faultGroup.faults.size) {
                                FaultAddingItem(faultGroup.faults[it], onAddFaultInPlaceClicked)
                            }
                        }
                    }
                }
            }

        }
    }
}

@PreviewTablet
@Composable
private fun PreviewFaultItem() {
    AppTheme {
        FaultGroupItem(FaultGroup.ForPreview, { _, _ -> })
    }
}