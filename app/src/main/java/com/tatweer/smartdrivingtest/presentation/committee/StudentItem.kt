package com.tatweer.smartdrivingtest.presentation.committee

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.StudentStatus
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultDp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    isTestStarted: Boolean = false,
    onLongClick: (Student) -> Unit = {},
    onAttendOnBusClick: (Student) -> Unit = {},
    onStartTestClick: (Student) -> Unit = {},
    onAbsentClick: (Student) -> Unit = {},
    onDeselected: () -> Unit = {},
) {
    var cardContentHeight by remember { mutableStateOf(IntSize.Zero) }
    Box {
        ElevatedCard(
            modifier
                .onSizeChanged { cardContentHeight = it }
                .clip(CardDefaults.shape)
                .combinedClickable(
                    onLongClick = { onLongClick(student) },
                    onLongClickLabel = "Long Click to show the Options",
                    onClickLabel = null,
                    onClick = {}
                )
        ) {
            Box(Modifier) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(DefaultDp)

                ) {
                    Box(
                        Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.temp_profile_pic),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .padding(DefaultDp)
                                .clip(RoundedCornerShape(ThreeQuarteredDoubleDefaultDp))
                                .border(
                                    0.5.dp,
                                    color = Gray,
                                    RoundedCornerShape(ThreeQuarteredDoubleDefaultDp)
                                )

                        )
                    }
                    Spacer(Modifier.width(QuarterDefaultDp))
                    Column(
                        Modifier.weight(1.5f),
                        verticalArrangement = Arrangement.spacedBy(QuarterDefaultDp)
                    ) {
                        Surface(Modifier.clip(RoundedCornerShape(HalfDefaultDp))) {
                            Text(
                                "1231242354",
                                Modifier
                                    .padding(
                                        horizontal = DefaultDp
                                    ),
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = ThreeQuarteredDefaultSp,
                            )
                        }
                        Text(
                            student.id.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = DefaultSp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            "ID: 7843000214290481249",
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = HalfDefaultSp
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
                                "Complete",
                                fontSize = DefaultSp
                            )
                        }
                    }
                }
                if (selected) {
                    Column(
                        Modifier
                            .requiredSize(
                                with(LocalDensity.current) {
                                    cardContentHeight.width.toDp()
                                },
                                with(LocalDensity.current) {
                                    cardContentHeight.height.toDp()
                                }
                            )
                            .background(Gray.copy(0.5f))
                            .clickable { onDeselected() },
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isTestStarted) {
                            Button(onClick = { onStartTestClick(student) }) {
                                Text(stringResource(R.string.label_start_test).uppercase())
                            }
                        } else {
                            Button(onClick = { onAttendOnBusClick(student) }) {
                                Text(stringResource(R.string.label_attend_on_bus).uppercase())
                            }
                        }
                        Button(
                            onClick = { onAbsentClick(student) },
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.onErrorContainer
                            )
                        ) {
                            Text(stringResource(R.string.label_absent).uppercase())
                        }
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
private fun StudentCardPreview() {
    AppTheme {
        StudentItem(Student(
            id = 3932,
            name = "Benjamin Ramos",
            studentId = "tristique",
            status = StudentStatus.NotStarted
        ))
    }
}