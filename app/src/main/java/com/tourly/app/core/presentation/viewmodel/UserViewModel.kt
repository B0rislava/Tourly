package com.tourly.app.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tourly.app.core.domain.usecase.GetUserProfileUseCase
import com.tourly.app.core.presentation.state.UserUiState
import com.tourly.app.core.storage.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        observeToken()
    }

    private fun observeToken() {
        viewModelScope.launch {
            tokenManager.getTokenFlow().collectLatest { token ->
                if (token != null) {
                    fetchUserProfile(token)
                } else {
                    _uiState.value = UserUiState.Idle
                }
            }
        }
    }

    private suspend fun fetchUserProfile(token: String) {
        _uiState.value = UserUiState.Loading
        
        getUserProfileUseCase(token)
            .onSuccess { user ->
                _uiState.value = UserUiState.Success(user)
            }
            .onFailure { error ->
                _uiState.value = UserUiState.Error(error.message ?: "Failed to load profile")
            }
    }
}
