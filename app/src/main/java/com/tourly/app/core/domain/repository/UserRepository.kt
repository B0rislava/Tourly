package com.tourly.app.core.domain.repository

import com.tourly.app.core.network.model.UserDTO

interface UserRepository {
    suspend fun getUserProfile(token: String): Result<UserDTO>
}
