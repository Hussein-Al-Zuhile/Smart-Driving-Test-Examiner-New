package com.tatweer.smartdrivingtest.presentation.driveTest.summary

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.base.component.BigButton
import com.tatweer.smartdrivingtest.presentation.base.component.BigOutlinedButton
import com.tatweer.smartdrivingtest.presentation.committee.StudentItemSharedContentKeys
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialog
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialogStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.FaultItem
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultDp
import com.tatweer.smartdrivingtest.utils.ConsumeEach
import com.tatweer.smartdrivingtest.utils.optionalSharedElement
import org.koin.androidx.compose.koinViewModel

private enum class Tab(@StringRes val titleResource: Int) {
    AllFaults(R.string.label_all_faults),
    ByExaminer(R.string.label_by_examiner),
    BySystem(R.string.label_by_system),
}

@Composable
fun SummaryScreen(
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var isAddManualFaultDialogOpen by rememberSaveable { mutableStateOf(false) }
    Box {
        Row(modifier.fillMaxSize()) {
            Sidebar(Student.ForPreview, onHomeScreenEvent, onAddManualFaultClicked = {
                isAddManualFaultDialogOpen = true
            }, Modifier.fillMaxWidth(0.25f))
            FaultyStudentsList()
        }

        if (isAddManualFaultDialogOpen) {
            Dialog(
                onDismissRequest = { isAddManualFaultDialogOpen = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                val viewModel: AddManualFaultViewModel = koinViewModel()
                viewModel.singleStateEventChannel.ConsumeEach {
                    when (it) {
                        AddManualFaultDialogStateEvent.NavigateUp -> isAddManualFaultDialogOpen =
                            false

                        is AddManualFaultDialogStateEvent.ConfirmEditingFaults -> {
                            // Handle calling API to add manual faults
                            isAddManualFaultDialogOpen = false
                        }
                    }
                }
                AddManualFaultDialog(viewModel.state, viewModel::onEvent)
            }
        }
    }
}

@Composable
fun FaultyStudentsList(modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxSize()
            .padding(top = DefaultDp, bottom = DefaultDp, end = DefaultDp)
    ) {
        var selectedTab by rememberSaveable { mutableStateOf(Tab.ByExaminer) }
        Column {
            TabRow(
                selectedTab.ordinal,
                indicator = {
                    Box(
                        Modifier
                            .tabIndicatorOffset(it[selectedTab.ordinal])
                            .fillMaxHeight()
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(HalfDefaultDp)
                            )
                    )
                },
                divider = {}, modifier = Modifier
                    .padding(HalfDefaultDp)
                    .clip(RoundedCornerShape(HalfDefaultDp))
            ) {
                Tab.entries.forEach { tab ->
                    val scaleAnimatable by animateFloatAsState(
                        if (selectedTab == tab) 1.25f else 1f,
                        label = "scaleAnimatable",
                        animationSpec = tween(2000)
                    )
                    Tab(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab }) {
                        Text(
                            stringResource(tab.titleResource),
                            Modifier
                                .padding(HalfDefaultDp)
                                .scale(scaleAnimatable),
                        )
                    }
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(HalfDefaultDp),
                verticalArrangement = Arrangement.spacedBy(HalfDefaultDp)
            ) {
                items(10) {
                    FaultItem()
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun Sidebar(
    student: Student,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    onAddManualFaultClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxHeight()
            .padding(DefaultDp)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(DefaultDp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(DefaultDp),
        ) {
            Image(
                painterResource(R.drawable.temp_profile_pic),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(DefaultDp))
                    .optionalSharedElement(StudentItemSharedContentKeys.Image),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(HalfDefaultDp))
            ) {
                Text(
                    "ID: ${student.emiratesId}",
                    Modifier
                        .optionalSharedElement(StudentItemSharedContentKeys.EmiratesId),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                student.name, Modifier.optionalSharedElement(StudentItemSharedContentKeys.Name),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                student.id.toString(),
                Modifier.optionalSharedElement(StudentItemSharedContentKeys.Id)
            )

            Text(
                "3",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.error
            )
            Text(
                stringResource(R.string.text_errors).uppercase(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.error
            )
            Text("Accepted: 03", style = MaterialTheme.typography.titleLarge)
            Text("Rejected: 01", style = MaterialTheme.typography.titleLarge)
            Text("Time: 21:20", style = MaterialTheme.typography.titleLarge)

            BigOutlinedButton(onClick = onAddManualFaultClicked) {
                Text(stringResource(R.string.label_add_manual_fault))
            }
            BigButton(onClick = { onHomeScreenEvent(HomeScreenEvent.OnStudentTestSubmitted("")) }) {
                Text(stringResource(R.string.label_submit))
            }
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewSummaryScreen() {
    AppTheme {
        SummaryScreen({})
    }
}
