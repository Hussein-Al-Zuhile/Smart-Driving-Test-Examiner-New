package com.tatweer.smartdrivingtest.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.theme.AppColors
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.SmartDrivingTestExaminerNewTheme
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultDp
import kotlinx.serialization.Serializable


@Composable
fun HomeNavigationRail(
    currentDestination: HomeNavigationDestinations,
    onDestinationSelected: (HomeNavigationDestinations) -> Unit,
    onLogoutClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clip(RoundedCornerShape(ThreeQuarteredDoubleDefaultDp))) {
        Image(
            painterResource(R.drawable.bg_login_screen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        NavigationRail(containerColor = Color.Transparent, header = {
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
            Text("Ayman Mahranin", color = Color.White)
            Text("37801", color = Color.White)
        }) {
            HomeNavigationDestinations.values().forEach {
                HomeNavigationItem(
                    item = it,
                    selected = currentDestination == it,
                    onDestinationSelected = onDestinationSelected
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
                Image(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = null,
                    Modifier.padding(vertical = DefaultDp),
                )
                Text(
                    stringResource(R.string.logout).uppercase(),
                    color = AppColors.LightRed
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeNavigationRailPreview() {
    SmartDrivingTestExaminerNewTheme {
        var currentDestination by remember {
            mutableStateOf<HomeNavigationDestinations>(HomeNavigationDestinations.DriveTest)
        }
        HomeNavigationRail(currentDestination, {
            currentDestination = it
        }, {}, Modifier.width(150.dp))
    }
}

@Serializable
sealed class HomeNavigationDestinations(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    @Serializable
    data object DriveTest : HomeNavigationDestinations(
        R.string.drive_test,
        icon = R.drawable.ic_drive_test_selected,
        unselectedIcon = R.drawable.ic_drive_test_unselected
    )

    @Serializable
    data object StudentList : HomeNavigationDestinations(
        R.string.student_list,
        icon = R.drawable.ic_student_list_selected,
        unselectedIcon = R.drawable.ic_student_list_unselected
    )

    companion object {
        fun values(): Array<HomeNavigationDestinations> {
            return arrayOf(DriveTest, StudentList)
        }

    }
}

@Composable
fun HomeNavigationItem(
    item: HomeNavigationDestinations,
    selected: Boolean,
    onDestinationSelected: (HomeNavigationDestinations) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .clickable(enabled) { onDestinationSelected(item) }
            .fillMaxWidth()
            .background(
                when {
                    !enabled -> Color.LightGray.copy(alpha = 0.5f)
                    selected -> Color.White
                    else -> Color.Transparent
                }
            )
            .padding(horizontal = DefaultDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                if (selected)
                    item.icon
                else
                    item.unselectedIcon
            ),
            contentDescription = null,
            Modifier
                .padding(vertical = DefaultDp)
                .fillMaxWidth(),
        )
        Text(
            stringResource(item.title),
            color = if (selected) MaterialTheme.colorScheme.primary else Color.White
        )
    }
}

@Preview
@Composable
private fun HomeNavigationItemPreview() {
    SmartDrivingTestExaminerNewTheme {
        HomeNavigationItem(
            item = HomeNavigationDestinations.DriveTest,
            selected = true,
            onDestinationSelected = {}
        )
    }
}