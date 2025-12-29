package com.tourly.app.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val message: String,
    val email: String
)
