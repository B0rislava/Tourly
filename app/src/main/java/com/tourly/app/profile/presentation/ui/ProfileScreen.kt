package com.tourly.app.profile.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tourly.app.core.presentation.state.UserUiState
import com.tourly.app.core.presentation.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    onLogout: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userState by userViewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = userState) {
            is UserUiState.Idle -> {
                Text(
                    text = "No user data available",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            is UserUiState.Loading -> {
                CircularProgressIndicator()
            }
            is UserUiState.Success -> {
                ProfileContent(
                    firstName = state.user.firstName,
                    lastName = state.user.lastName,
                    email = state.user.email,
                    profilePictureUrl = state.user.profilePictureUrl,
                    onLogout = onLogout,
                    onNavigateToSettings = onNavigateToSettings
                )
            }
            is UserUiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    ProfileContent(
        firstName = "Ashley",
        lastName = "Watson",
        email = "ashley.watson@example.com",
        profilePictureUrl = null,
        onLogout = {},
        onNavigateToSettings = {}
    )
}