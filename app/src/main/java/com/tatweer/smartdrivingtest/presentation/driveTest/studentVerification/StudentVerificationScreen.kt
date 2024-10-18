@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.committee.StudentItem
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.utils.optionalSharedBounds


@Composable
fun StudentVerificationScreen(
    homeScreenState: HomeScreenState,
    state: StudentVerificationScreenState,
    onEvent: (StudentVerificationScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
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
                Modifier.fillMaxWidth(0.4f)
            )
            StudentItem(
                homeScreenState.startedStudent,
                Modifier
                    .fillMaxWidth(0.5f),
            )

            CircularProgressIndicator()
            Text("Please put the EID on the back of the tablet to read it")

            Row {
                Spacer(Modifier.weight(0.5f))
                OutlinedButton(onClick = { onEvent(StudentVerificationScreenEvent.OnCancelClicked) }) {
                    Text(stringResource(R.string.label_cancel))
                }
                Spacer(Modifier.weight(1f))
                OutlinedButton(
                    onClick = { onEvent(StudentVerificationScreenEvent.OnSkipClicked) },
                ) {
                    Text(stringResource(R.string.label_skip_auto_verification))
                }
                Spacer(Modifier.weight(0.5f))
            }
        }
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No Student")
    }
}

@PreviewTablet
@Composable
private fun PreviewUserVerificationScreen() {
    AppTheme {
        AnimatedContent(Unit, label = "") {
            it
            StudentVerificationScreen(
                HomeScreenState(startedStudent = Student.ForPreview),
                StudentVerificationScreenState.Initial,
                {},
            )
        }
    }
}