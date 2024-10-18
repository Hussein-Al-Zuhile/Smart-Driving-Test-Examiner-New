package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.committee.StudentItem
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun StudentVerificationSkipScreen(
    homeScreenState: HomeScreenState,
    state: StudentVerificationSkipScreenState,
    onEvent: (StudentVerificationSkipScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    homeScreenState.startedStudent?.let {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(R.drawable.logo_smart_driving_test_white),
                null,
                Modifier.fillMaxWidth(0.2f)
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(Modifier.fillMaxWidth(0.3f)) {
                    StudentItem(
                        homeScreenState.startedStudent,
                        Modifier
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(DefaultDp))
                    OutlinedTextField(
                        "",
                        onValueChange = {},
                        Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        label = { Text(stringResource(R.string.label_add_comment)) },
                        minLines = 3,
                    )
                }
                Column(
                    Modifier.fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.temp_profile_pic),
                        contentDescription = "Student image from the camera",
                        Modifier.clip(RoundedCornerShape(DefaultDp))
                    )
                    var isTakePhotoClicked by remember { mutableStateOf(false) }
                    val timer by animateFloatAsState(
                        if (isTakePhotoClicked) 0f else 1f,
                        animationSpec = tween(durationMillis = 3000, easing = LinearEasing),
                        label = "Timer",
                        finishedListener = {
                            onEvent(StudentVerificationSkipScreenEvent.OnPhotoTaken)
                        }
                    )
                    Spacer(Modifier.height(DefaultDp))
                    Box(
                        Modifier
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            { timer },
                            Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f),
                            strokeWidth = HalfDefaultDp
                        )
                        Text(
                            text = "${(timer * 3 + 0.99).toInt()}",
                            Modifier
                                .padding(all = DoubleDefaultDp),
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(Modifier.height(DefaultDp))
                    // TODO: Just mimic, need to be according to the business logic in future
                    if (!state.isPhotoTaken) {
                        Button({ isTakePhotoClicked = true }) {
                            Text(stringResource(R.string.label_take_photo))
                        }
                    } else {
                        OutlinedButton({
                            isTakePhotoClicked = false
                            isTakePhotoClicked = true
                        }) {
                            Text(stringResource(R.string.label_retry))
                        }
                    }
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedButton(onClick = { onEvent(StudentVerificationSkipScreenEvent.OnCancelClicked) }) {
                    Text(stringResource(R.string.label_cancel))
                }
                Button(
                    onClick = { onEvent(StudentVerificationSkipScreenEvent.OnSkipScreenClicked) },
                    enabled = state.isPhotoTaken
                ) {
                    Text(stringResource(R.string.label_skip))
                }
            }
        }
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No Student")
    }
}

@PreviewTablet
@Composable
private fun PreviewUserVerificationSkipScreen() {
    AppTheme {
        StudentVerificationSkipScreen(HomeScreenState(), StudentVerificationSkipScreenState(), {})
    }
}