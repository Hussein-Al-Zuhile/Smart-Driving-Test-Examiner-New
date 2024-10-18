package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Fault
import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.FaultItem
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun FaultGroupItem(
    faultGroup: FaultGroup,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    Surface(modifier) {
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
                slideInVertically() togetherWith slideOutVertically()
            }) {
                if (it) {
                    Column {
                        faultGroup.faults.forEach {
                            FaultItem()
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
        FaultGroupItem(FaultGroup.ForPreview)
    }
}