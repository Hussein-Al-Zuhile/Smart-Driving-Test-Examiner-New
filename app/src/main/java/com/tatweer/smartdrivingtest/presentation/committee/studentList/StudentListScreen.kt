package com.tatweer.smartdrivingtest.presentation.committee.studentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Forward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablets
import com.tatweer.smartdrivingtest.presentation.committee.StudentItem
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme

@Composable
fun StudentListScreen(
    state: StudentListScreenState,
    onEvent: (StudentListScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(state.errorMessage, modifier = Modifier.align(Alignment.Center))
            }

            state.students.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier,
                    contentPadding = PaddingValues(DefaultDp),
                    verticalArrangement = Arrangement.spacedBy(DefaultDp),
                    horizontalArrangement = Arrangement.spacedBy(DefaultDp),
                ) {
                    items(state.students, key = (Student::id)) { student ->
                        StudentItem(student)
                    }
                }
                ExtendedFloatingActionButton(
                    onClick = {
                        onEvent(StudentListScreenEvent.OnNextClicked)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(DefaultDp),
                    text = { Text("Start Test") },
                    icon = { Icon(Icons.AutoMirrored.Filled.Forward, "Next") },
                )
            }
        }

    }
}

@PreviewTablets
@Composable
private fun StudentListPreview() {
    AppTheme {
        StudentListScreen(
            state = StudentListScreenState(
                listOf(
                    Student(1),
                    Student(2),
                    Student(3)
                )
            ), {})
    }
}