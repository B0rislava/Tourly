package com.tourly.app.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourly.app.core.navigation.Route
import com.tourly.app.core.storage.TokenManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = HomeViewModel.Factory::class)
class HomeViewModel @AssistedInject constructor(
    @Assisted val route: Route.Home,
    private val tokenManager: TokenManager
) : ViewModel() {

    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            tokenManager.clearToken()
            onLogoutSuccess()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(route: Route.Home): HomeViewModel
    }
}
