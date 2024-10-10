package com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
fun TestRouteSelectionScreen(
    state: TestRouteSelectionScreenState,
    onEvent: (TestRouteSelectionScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(R.string.text_select_vehicle_route),
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
        LazyRow(
            modifier = Modifier.align(CenterHorizontally),
            contentPadding = PaddingValues(DefaultDp),
            horizontalArrangement = Arrangement.spacedBy(DefaultDp),
        ) {
            items(state.testRoutes) {
                TestRouteItem(
                    it,
                    state.selectedTestRoute == it,
                    onItemClick = {
                        onEvent(TestRouteSelectionScreenEvent.OnTestRouteSelected(it))
                    })
            }
        }
        Spacer(Modifier.weight(1f))

        Button(
            onClick = { onEvent(TestRouteSelectionScreenEvent.OnConfirmClicked) },
            Modifier
                .padding(DoubleDefaultDp)
                .align(Alignment.End),
            enabled = state.selectedTestRoute != null
        ) {
            Text(stringResource(R.string.label_confirm), Modifier.padding(HalfDefaultDp))
        }
    }
}

@PreviewTablet
@Composable
private fun RouteSelectionScreenPreview() {
    AppTheme {
        TestRouteSelectionScreen(
            TestRouteSelectionScreenState(),
            {})
    }
}