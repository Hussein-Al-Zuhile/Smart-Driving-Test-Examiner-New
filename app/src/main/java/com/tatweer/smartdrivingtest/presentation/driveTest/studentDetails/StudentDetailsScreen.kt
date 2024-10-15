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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp

@Composable
fun StudentDetailsScreen(
    homeScreenState: HomeScreenState,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    onStudentDetailsScreenEvent: (StudentDetailsScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    homeScreenState.startedStudent?.let {
        Column(modifier.fillMaxSize()) {
            Card(
                Modifier
                    .weight(1f)
                    .padding(DefaultDp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .padding(DefaultDp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .width(IntrinsicSize.Max)
                            .padding(DefaultDp)
                            .weight(1f),
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = HalfDefaultDp)
                                .clip(RoundedCornerShape(HalfDefaultDp))
                        ) {
                            Text(
                                "ID: ${homeScreenState.startedStudent.id}",
                                Modifier.padding(QuarterDefaultDp),
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                        Image(
                            painter = painterResource(R.drawable.temp_profile_pic),
                            contentDescription = stringResource(R.string.description_student_image),
                            Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.height(DefaultDp))

                    }

                    Column(
                        Modifier
                            .weight(1f),
                    ) {
                        Text(
                            "Name: ${homeScreenState.startedStudent.name}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            "Student Id: ${homeScreenState.startedStudent.studentId}",
                            style = MaterialTheme.typography.headlineMedium
                        )
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
                                homeScreenState.startedStudent.status.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                    }

                    Column(
                        Modifier
                            .width(IntrinsicSize.Max)
                            .padding(DefaultDp)
                            .align(Alignment.Bottom)
                            .weight(1f),
                    ) {
                        Button(
                            onClick = { onStudentDetailsScreenEvent(StudentDetailsScreenEvent.OnVerifyStudentClicked) },
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(6f)
                        ) {
                            Text(stringResource(R.string.label_verify_student))
                        }
                        Spacer(Modifier.height(DefaultDp))
                        OutlinedButton(
                            onClick = {},
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(6f)
                        ) {
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
                        painter = painterResource(R.drawable.united_arab_emirates),
                        contentDescription = null,
                        Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
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
    } ?: Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(R.string.text_no_student_started))
    }
}

@PreviewTablet
@Composable
private fun PreviewUserDetailsScreen() {
    AppTheme {
        StudentDetailsScreen(
            homeScreenState = HomeScreenState(Student.Initial),
            onHomeScreenEvent = {},
            onStudentDetailsScreenEvent = {}
        )
    }
}