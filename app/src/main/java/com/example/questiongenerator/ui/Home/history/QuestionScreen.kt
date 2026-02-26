package com.example.questiongenerator.ui.Home.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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

@Composable
fun QuestionScreen(
    docId: String,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading
    val historyItem by viewModel.historyItem
    val error by viewModel.error

    LaunchedEffect(docId) {
        viewModel.loadMcq(docId)
    }

    Box(Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            error != null -> Text(
                text = error ?: "",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )

            historyItem != null -> LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {

                item {
                    Text(
                        text = historyItem!!.title,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(Modifier.height(16.dp))
                }

                items(historyItem!!.units) { unit ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {

                            Text(unit.unit, style = MaterialTheme.typography.titleMedium)
                            Text(unit.title, style = MaterialTheme.typography.labelMedium)

                            Spacer(Modifier.height(6.dp))

                            Text(
                                text = unit.mcqs,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}