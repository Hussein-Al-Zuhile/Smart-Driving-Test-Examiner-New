package com.tatweer.smartdrivingtest.presentation.systemHealth

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scaleMatrix
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.SystemHealth
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import kotlin.random.Random

enum class Tabs(@StringRes val titleResource: Int) {
    WorkingSystems(R.string.label_working_systems),
    AllSystems(R.string.label_all_systems),
    FailedSystems(R.string.label_failed_systems)
}


@Composable
fun SystemHealthScreen(
    modifier: Modifier = Modifier
) {
    Column(Modifier.padding(DefaultDp)) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        TabRow(
            selectedTabIndex,
            indicator = {
                val selectedTab = it[selectedTabIndex]
                Box(
                    modifier
                        .tabIndicatorOffset(selectedTab)
                        .fillMaxHeight()
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.secondary,
                            RoundedCornerShape(HalfDefaultDp)
                        )
                )
            },
            divider = {}, modifier = Modifier.clip(RoundedCornerShape(HalfDefaultDp))
        ) {
            Tabs.entries.forEachIndexed { index, filterType ->
                val scaleAnimatable by animateFloatAsState(
                    if (selectedTabIndex == index) 1.25f else 1f,
                    label = "scaleAnimatable",
                    animationSpec = tween(2000)
                )
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }) {
                    Text(
                        stringResource(filterType.titleResource),
                        Modifier
                            .padding(HalfDefaultDp)
                            .scale(scaleAnimatable),
                    )
                }
            }
        }
        Spacer(Modifier.height(DefaultDp))
        LazyVerticalGrid(
            GridCells.Fixed(4),
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(DefaultDp),
            horizontalArrangement = Arrangement.spacedBy(DefaultDp)
        ) {
            items(10) {
                SystemHealthItem(
                    SystemHealth(
                        id = 9553,
                        title = "Gear is not working",
                        isWorking = Random.nextBoolean()
                    ),
                    Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewSystemHealthScreen() {
    AppTheme {
        Surface {
            SystemHealthScreen()
        }
    }
}