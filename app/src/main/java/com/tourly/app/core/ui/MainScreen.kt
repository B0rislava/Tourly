package com.tourly.app.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tourly.app.chat.presentation.ui.ChatScreen
import com.tourly.app.core.ui.components.BottomNavDestination
import com.tourly.app.core.ui.components.BottomNavigationBar
import com.tourly.app.core.ui.components.SimpleTopBar
import com.tourly.app.dashboard.presentation.ui.DashboardScreen
import com.tourly.app.home.presentation.ui.HomeScreen
import com.tourly.app.profile.presentation.ui.ProfileScreen
import com.tourly.app.core.ui.utils.WindowSizeState

@Composable
fun MainScreen(
    windowSizeState: WindowSizeState,
    onLogout: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDestination by rememberSaveable {
        mutableStateOf(BottomNavDestination.HOME)
    }

    Scaffold(
        topBar = {
            SimpleTopBar(title = selectedDestination.label)
        },
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = selectedDestination,
                onDestinationSelected = { destination ->
                    selectedDestination = destination
                },
            )
        },
        modifier = modifier
    ) { paddingValues ->
        when (selectedDestination) {
            BottomNavDestination.HOME -> {
                HomeScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.DASHBOARD -> {
                DashboardScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.CHAT -> {
                ChatScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            BottomNavDestination.PROFILE -> {
                ProfileScreen(
                    onLogout = onLogout,
                    onNavigateToSettings = onNavigateToSettings,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

