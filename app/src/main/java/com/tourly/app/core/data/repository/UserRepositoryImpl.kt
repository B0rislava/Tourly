package com.tourly.app.core.data.repository

import com.tourly.app.core.network.api.AuthApiService
import com.tourly.app.core.network.model.UserDTO
import com.tourly.app.core.network.util.APIException
import com.tourly.app.core.domain.repository.UserRepository
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : UserRepository {

    override suspend fun getUserProfile(token: String): Result<UserDTO> {
        return try {
            val response = authApiService.getProfile(token)
            if (response.status.isSuccess()) {
                val user = response.body<UserDTO>()
                Result.success(user)
            } else {
                val errorBody = response.bodyAsText()
                Result.failure(APIException(response.status.value, "Profile fetch failed: $errorBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
