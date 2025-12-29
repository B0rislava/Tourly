package com.tourly.app.core.network.model

import com.tourly.app.login.domain.UserRole
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: UserRole
)
