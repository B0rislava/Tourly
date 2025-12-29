package com.tourly.app.core.domain.repository

import com.tourly.app.core.network.model.UserDto

interface UserRepository {
    suspend fun getUserProfile(token: String): Result<UserDto>
}
