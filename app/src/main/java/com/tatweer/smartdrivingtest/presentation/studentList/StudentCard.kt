package com.tatweer.smartdrivingtest.presentation.studentList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.theme.AppColors.Gray
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.SmartDrivingTestExaminerNewTheme
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultDp

@Composable
fun StudentCard(modifier: Modifier = Modifier) {
    Card(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(DefaultDp)
        ) {
            var textsHeight by remember { mutableStateOf(0) }
            Image(
                painter = painterResource(R.drawable.temp_profile_pic),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(textsHeight.dp)
                    .clip(RoundedCornerShape(ThreeQuarteredDoubleDefaultDp))
                    .border(0.5.dp, color = Gray, RoundedCornerShape(ThreeQuarteredDoubleDefaultDp))

            )
            Spacer(Modifier.width(HalfDefaultDp))
            Column(
                Modifier
                    .onSizeChanged {
                        textsHeight = it.height
                    },
                verticalArrangement = Arrangement.spacedBy(HalfDefaultDp),
            ) {
                Text(
                    "1231242354",
                    Modifier
                        .background(Color.White, RoundedCornerShape(HalfDefaultDp))
                        .padding(
                            horizontal = DefaultDp
                        ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.height(HalfDefaultDp))
                Text(
                    "Name: Salem Mohamad",
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    "ID: 7843000214290481249",
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(Modifier.height(HalfDefaultDp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    Box(
                        Modifier
                            .height(IntrinsicSize.Min)
                            .aspectRatio(1f)
                            .padding(QuarterDefaultDp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                    Text("Complete")
                }
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun StudentCardPreview() {
    SmartDrivingTestExaminerNewTheme {
        StudentCard()
    }
}