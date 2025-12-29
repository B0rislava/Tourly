package com.tourly.app.core.presentation.state

import com.tourly.app.core.network.model.UserDto

sealed interface UserUiState {
    data object Idle : UserUiState
    data object Loading : UserUiState
    data class Success(val user: UserDto) : UserUiState
    data class Error(val message: String) : UserUiState
}
