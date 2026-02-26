package com.example.questiongenerator.ui.Home.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.questiongenerator.ui.Home.navigation.Question
import com.example.questiongenerator.ui.theme.Orange
import com.example.questiongenerator.util.DateUtils

@Composable
fun HistoryScreen(
    navController: NavHostController,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading
    val history by viewModel.historyList
    val error by viewModel.error

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    Box(Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            error != null -> {
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            history.isEmpty() -> {
                Text(
                    text = "No History Yet",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn {
                    items(history) { item ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(14.dp)
                            ) {


                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(Modifier.height(6.dp))

                                Text(
                                    text = DateUtils.format(item.timestamp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray
                                )

                                Spacer(Modifier.height(6.dp))

                                Text(
                                    text = "Units: ${item.units.size}",
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Spacer(Modifier.height(12.dp))

                                Button(
                                    onClick = {
                                        navController.navigate(
                                            Question(item.id)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(Orange)
                                ) {
                                    Text("Open MCQs")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}