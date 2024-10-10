package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp

@Composable
fun StudentDetailsScreen(
    state: StudentDetailsScreenState,
    onEvent: (StudentDetailsScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        Card(
            Modifier
                .weight(1f)
                .padding(DefaultDp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Row(
                Modifier
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .width(IntrinsicSize.Max)
                        .padding(DefaultDp),
                ) {
                    Surface(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "ID 2131231231231",
                            Modifier.padding(QuarterDefaultDp),
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.temp_profile_pic),
                        contentDescription = stringResource(R.string.description_student_image),
                        Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .fillMaxHeight(0.7f)
                    )
                    Spacer(Modifier.height(DefaultDp))

                }

                Column(
                    Modifier
                        .weight(1f),
                ) {
                    Text("Name: Ahmed Mohamed")
                    Text("Student Id: 123213123")
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
                                .background(MaterialTheme.colorScheme.error)
                        )
                        Text(
                            "Complete",
                            fontSize = DefaultSp
                        )
                    }

                }

                Column(
                    Modifier
                        .width(IntrinsicSize.Max)
                        .padding(DefaultDp)
                        .align(Alignment.Bottom),
                ) {
                    Button(
                        onClick = {onEvent(StudentDetailsScreenEvent.OnVerifyStudentClicked)},
                        Modifier
                    ) {
                        Text(stringResource(R.string.label_verify_student))
                    }
                    OutlinedButton(onClick = {}, Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.label_absent))
                    }
                }
            }

        }
        Card(
            Modifier
                .weight(0.5f)
                .padding(DefaultDp),
        ) {
            Box {

                Image(
                    painter = painterResource(R.drawable.bg_login_screen),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                FilledTonalButton(
                    onClick = {},
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(DefaultDp)
                ) {
                    Text(stringResource(R.string.label_change_route))
                }
            }
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewUserDetailsScreen() {
    AppTheme {
        StudentDetailsScreen(state = StudentDetailsScreenState.Initial, onEvent = {})
    }
}