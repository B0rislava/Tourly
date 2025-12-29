package com.tourly.app.core.presentation.state

import com.tourly.app.core.network.model.UserDTO

sealed interface UserUiState {
    data object Loading : UserUiState
    data object Idle : UserUiState
    data class Success(val user: UserDTO) : UserUiState
    data class Error(val message: String) : UserUiState
}
