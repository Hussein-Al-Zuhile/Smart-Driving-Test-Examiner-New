package com.tatweer.smartdrivingtest.presentation.systemHealth

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.SystemHealth
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.FaultItem
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import kotlin.random.Random

private enum class Tab(@StringRes val titleResource: Int) {
    AllSystems(R.string.label_all_systems),
    WorkingSystems(R.string.label_working_systems),
    FailedSystems(R.string.label_failed_systems)
}

// TODO: Remove this when the API is ready
private val ListForTestingOnly: List<SystemHealth> = listOf(
    SystemHealth(id = 8218, title = "augue", isWorking = true),
    SystemHealth(id = 2085, title = "lacinia", isWorking = true),
    SystemHealth(id = 7499, title = "errem", isWorking = false),
    SystemHealth(id = 2849, title = "similique", isWorking = true),
    SystemHealth(id = 8161, title = "adolescens", isWorking = false),
    SystemHealth(id = 8111, title = "Aasdasd", isWorking = true),
    SystemHealth(id = 831, title = "Adsdasdas", isWorking = false),
    SystemHealth(id = 8671, title = "possit", isWorking = false)
)

@Composable
fun SystemHealthScreen(
    modifier: Modifier = Modifier
) {
    Column(Modifier.padding(DefaultDp)) {
        var selectedTab by remember { mutableStateOf(Tab.AllSystems) }
        TabRow(
            selectedTab.ordinal,
            indicator = {
                Box(
                    modifier
                        .tabIndicatorOffset(it[selectedTab.ordinal])
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
            Tab.entries.forEach { tab ->
                val scaleAnimatable by animateFloatAsState(
                    if (selectedTab == tab) 1.25f else 1f,
                    label = "scaleAnimatable",
                    animationSpec = tween(2000)
                )
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab }) {
                    Text(
                        stringResource(tab.titleResource),
                        Modifier
                            .padding(HalfDefaultDp)
                            .scale(scaleAnimatable),
                    )
                }
            }
        }

        val systemHealthList by remember {
            derivedStateOf {
                when (selectedTab) {
                    Tab.WorkingSystems -> ListForTestingOnly.filter { it.isWorking }
                    Tab.AllSystems -> ListForTestingOnly
                    Tab.FailedSystems -> ListForTestingOnly.filter { !it.isWorking }
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
            items(systemHealthList, key = { it.id }) {
                SystemHealthItem(it, Modifier.animateItem())
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