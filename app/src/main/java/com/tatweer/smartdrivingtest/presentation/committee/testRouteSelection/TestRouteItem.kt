package com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.TestRoute
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp

@Composable
fun TestRouteItem(
    testRoute: TestRoute,
    selected: Boolean,
    onItemClick: (TestRoute) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var imageSize: IntSize by remember { mutableStateOf(IntSize.Zero) }
        Box(
            Modifier
                .clip(MaterialTheme.shapes.medium)
                .clickable { onItemClick(testRoute) }) {
            Image(
                painter = painterResource(R.drawable.temp_profile_pic),
                contentDescription = null,
                Modifier
                    .heightIn(max = 200.dp)
                    .onSizeChanged {
                        imageSize = it
                    },
            )
            val density = LocalDensity.current
            if (selected) {
                with(density) {
                    Box(
                        Modifier
                            .size(imageSize.width.toDp(), imageSize.height.toDp())
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    )
                }
            }
        }
        Spacer(Modifier.height(DefaultDp))
        Text("Test Route # 1", style = MaterialTheme.typography.titleMedium)

    }
}

@Preview
@Composable
private fun RouteItemPreview() {
    AppTheme {
        TestRouteItem(
            TestRoute(1),
            true,
            {})
    }
}