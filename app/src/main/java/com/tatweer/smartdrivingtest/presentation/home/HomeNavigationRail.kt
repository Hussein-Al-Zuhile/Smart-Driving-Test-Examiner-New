package com.tatweer.smartdrivingtest.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import kotlinx.serialization.Serializable


@Composable
fun HomeNavigationRail(
    currentDestination: HomeNavigationDestinations,
    onDestinationSelected: (HomeNavigationDestinations) -> Unit,
    onLogoutClicked: () -> Unit,
    isStudentTestStarted: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clip(CardDefaults.elevatedShape)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {

        Column(
            Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(Modifier.height(HalfDefaultDp))
            Image(
                painterResource(R.drawable.temp_profile_pic),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f)
                    .border(1.dp, Color.White, CircleShape)
            )
            Text(
                "Ayman Mahranin",
                modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            Text(
                "37801",
                modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            HomeNavigationDestinations.values().forEach {
                HomeNavigationItem(
                    item = it,
                    selected = currentDestination == it,
                    onDestinationSelected = onDestinationSelected,
                    enabled = !(it == HomeNavigationDestinations.DriveTest && !isStudentTestStarted)
                )
            }
            Spacer(Modifier.weight(1f))
            // Logout
            Column(
                modifier = modifier
                    .clickable(onClick = onLogoutClicked)
                    .fillMaxWidth()
                    .padding(horizontal = DefaultDp)
                    .padding(bottom = HalfDefaultDp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = null,
                    Modifier
                        .padding(vertical = DefaultDp)
                        .fillMaxWidth(0.8f),
                )
                Text(stringResource(R.string.logout).uppercase())
            }
        }
    }
}

@PreviewTablet
@Composable
private fun HomeNavigationRailPreview() {
    AppTheme {
        var currentDestination by remember {
            mutableStateOf<HomeNavigationDestinations>(HomeNavigationDestinations.DriveTest)
        }
        HomeNavigationRail(currentDestination, {
            currentDestination = it
        }, {}, isStudentTestStarted = false, Modifier.width(150.dp))
    }
}

@Suppress("NO_REFLECTION_IN_CLASS_PATH")
@Serializable
sealed class HomeNavigationDestinations(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    companion object {
        fun fromRoute(route: String): HomeNavigationDestinations? {
            return HomeNavigationDestinations::class.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }?.objectInstance
        }

        fun values(): Array<HomeNavigationDestinations> {
            return arrayOf(DriveTest, Committee, SystemHealth)
        }
    }

    @Serializable
    data object DriveTest : HomeNavigationDestinations(
        R.string.drive_test,
        icon = R.drawable.ic_drive_test_selected
    )

    @Serializable
    data object Committee : HomeNavigationDestinations(
        R.string.committee,
        icon = R.drawable.ic_student_list_selected
    )

    @Serializable
    data object SystemHealth : HomeNavigationDestinations(
        R.string.system_health,
        icon = R.drawable.ic_system_health
    )
}

@Composable
fun HomeNavigationItem(
    item: HomeNavigationDestinations,
    selected: Boolean,
    onDestinationSelected: (HomeNavigationDestinations) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false
) {

    val scaleAnimatableNotifier by remember { mutableStateOf(Animatable(1f)) }
    LaunchedEffect(enabled) {
        if (enabled) {
            scaleAnimatableNotifier.animateTo(
                1.25f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            )
            scaleAnimatableNotifier.animateTo(1f)
        }
    }

    Card(
        modifier.scale(scaleAnimatableNotifier.value),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.surfaceContainer
            else
                Color.Transparent
        ),
        shape = RectangleShape,
    ) {
        Surface(
            color = when {
                !enabled -> IconButtonDefaults.filledIconToggleButtonColors().disabledContainerColor
                selected -> IconButtonDefaults.filledIconToggleButtonColors().checkedContainerColor
                else -> IconButtonDefaults.filledIconToggleButtonColors().containerColor
            },
            contentColor = when {
                !enabled -> IconButtonDefaults.filledIconToggleButtonColors().disabledContentColor
                selected -> IconButtonDefaults.filledIconToggleButtonColors().checkedContentColor
                else -> IconButtonDefaults.filledIconToggleButtonColors().contentColor
            },
        ) {
            Column(
                modifier = Modifier
                    .clickable(enabled) { onDestinationSelected(item) }
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(vertical = HalfDefaultDp)
                )
                Text(
                    stringResource(item.title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                )
                Spacer(Modifier.height(HalfDefaultDp))
            }
        }
    }
}

@Composable
private fun HomeNavigationItemPreview() {
    AppTheme {
        HomeNavigationItem(
            item = HomeNavigationDestinations.DriveTest,
            selected = true,
            onDestinationSelected = {}
        )
    }
}