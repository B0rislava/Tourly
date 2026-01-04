package com.tourly.app.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tourly.app.R
import com.tourly.app.chat.presentation.ui.ChatScreen
import com.tourly.app.core.presentation.ui.components.BottomNavDestination
import com.tourly.app.core.presentation.ui.components.BottomNavDestination.Companion.guideDestinations
import com.tourly.app.core.presentation.ui.components.BottomNavigationBar
import com.tourly.app.core.presentation.ui.components.SimpleTopBar
import com.tourly.app.dashboard.presentation.ui.DashboardScreen
import com.tourly.app.profile.presentation.ui.ProfileScreen
import com.tourly.app.home.presentation.ui.GuideHomeScreen

@Composable
fun GuideMainContent(
    modifier: Modifier = Modifier,
    selectedDestination: BottomNavDestination,
    isEditingProfile: Boolean,
    onCancelEdit: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onDestinationSelected: (BottomNavDestination) -> Unit,
    onNavigateToSettings: () -> Unit,
    onLogout: () -> Unit,
    onEditingStateChange: (Boolean, (() -> Unit)?) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            SimpleTopBar(
                title = if (isEditingProfile) {
                    stringResource(id = R.string.edit_profile)
                } else {
                    selectedDestination.label
                },
                navigationIcon = {
                    if (isEditingProfile) {
                        IconButton(onClick = onCancelEdit) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (selectedDestination == BottomNavDestination.PROFILE && !isEditingProfile) {
                        IconButton(onClick = onNavigateToSettings) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = stringResource(id = R.string.settings)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (!isEditingProfile) {
                BottomNavigationBar(
                    selectedDestination = selectedDestination,
                    destinations = guideDestinations,
                    onDestinationSelected = onDestinationSelected,
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        when (selectedDestination) {
            BottomNavDestination.GUIDE_HOME -> {
                GuideHomeScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.GUIDE_DASHBOARD -> {
                DashboardScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.CREATE_TOUR -> {
                Box(
                    modifier = Modifier.padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Create Tour Screen - Coming Soon")
                }
            }
            BottomNavDestination.CHAT -> {
                ChatScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.PROFILE -> {
                ProfileScreen(
                    snackbarHostState = snackbarHostState,
                    onLogout = onLogout,
                    onEditingStateChange = onEditingStateChange,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            else -> {}
        }
    }
}
