package com.example.questiongenerator.ui.Home.dashboard

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questiongenerator.ui.theme.Orange
import com.example.questiongenerator.util.FileUtil
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val title by viewModel.courseTitle
    val units by viewModel.units

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val file = FileUtil.from(context, it)
            viewModel.uploadPdf(file)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "SmartQ AI",
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { launcher.launch("application/pdf") },
            modifier = Modifier.size(width = 200.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Orange)
        ) {
            Text("Upload Syllabus PDF")
        }

        Spacer(Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text(it, color = Color.Red)
        }

        if (title.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(units) { unit ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = "${unit.unit}: ${unit.title}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = unit.mcqs,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}