package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.committee.StudentItem
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun StudentVerificationSkipScreen(
    modifier: Modifier = Modifier
) {
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
            Column(Modifier.fillMaxWidth(0.25f)) {
                StudentItem(Student(1), Modifier.fillMaxWidth())
                Spacer(Modifier.height(DefaultDp))
                OutlinedTextField(
                    "",
                    onValueChange = {},
                    Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    label = { Text(stringResource(R.string.label_add_comment)) },
                    minLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                )
            }
            Column(
                Modifier.fillMaxWidth(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.temp_profile_pic),
                    contentDescription = "Student image from the camera",
                )
                var isTakePhotoClicked by remember { mutableStateOf(false) }
                val timer by animateFloatAsState(
                    if (isTakePhotoClicked) 0f else 1f,
                    animationSpec = tween(durationMillis = 5000, easing = LinearEasing),
                    label = "Timer",
                    finishedListener = {

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
                Button({ isTakePhotoClicked = true }) {
                    Text("Take Photo")
                }
            }
        }

        Row {
            Spacer(Modifier.weight(0.5f))
            OutlinedButton(onClick = {}) {
                Text(stringResource(R.string.label_cancel))
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {},
            ) {
                Text(stringResource(R.string.label_skip))
            }
            Spacer(Modifier.weight(0.5f))
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewUserVerificationSkipScreen() {
    AppTheme {
        StudentVerificationSkipScreen()
    }
}