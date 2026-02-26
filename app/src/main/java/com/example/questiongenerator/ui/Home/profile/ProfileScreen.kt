package com.example.questiongenerator.ui.Home.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questiongenerator.ui.theme.Orange

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onSignOut: () -> Unit
) {

    val user = viewModel.user
    val isLoading by viewModel.isLoading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Profile",
            style = MaterialTheme.typography.displayMedium,
            letterSpacing = 2.sp,
        )

        Spacer(Modifier.height(80.dp))

        Text(
            text = "Name",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = user?.displayName ?: "No Name",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Email",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = user?.email ?: "No Email",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(62.dp))

        Button(
            onClick = {
                viewModel.signOut()
                onSignOut()
            },
            modifier = Modifier.size(width = 200.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(Orange)
        ) {
            Text("Sign Out")
        }

        Spacer(Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}