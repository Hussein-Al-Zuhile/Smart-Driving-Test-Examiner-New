package com.tatweer.smartdrivingtest.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tatweer.smartdrivingtest.domain.base.Result
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.theme.SmartDrivingTestExaminerNewTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                SmartDrivingTestExaminerNewTheme {
                    val viewModel: MainViewModel = koinViewModel()
                    val studentsResult =
                        viewModel.getAllStudents()
                            .collectAsStateWithLifecycle(initialValue = Result.initial())

                    println(studentsResult)

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(Modifier.padding(innerPadding)) {
                            when (studentsResult.value) {
                                is Result.Loading -> {
                                    CircularProgressIndicator()
                                }

                                is Result.Success.Data -> {
                                    val studentListState =
                                        rememberLazyListState((studentsResult.value as Result.Success.Data<List<Student>>).data.size)
                                    LazyColumn(state = studentListState) {
                                        items((studentsResult.value as Result.Success.Data<List<Student>>).data) { studentItem ->
                                            Row {
                                                Text(studentItem.name)
                                            }
                                        }
                                    }
                                }

                                is Result.Failure -> {

                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartDrivingTestExaminerNewTheme {
        Greeting("Android")
    }
}