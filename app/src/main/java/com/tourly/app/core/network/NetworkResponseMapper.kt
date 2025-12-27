package com.tourly.app.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

@Serializable
data class BackendErrorResponse(
    val code: String,
    val message: String,
    val description: String? = null,
    val errors: Map<String, String>? = null
)

object NetworkResponseMapper {
    
    suspend inline fun <reified T> map(response: HttpResponse): Result<T> {
        return try {
            if (response.status.isSuccess()) {
                val data = response.body<T>()
                Result.Success(data)
            } else {
                // Try to parse error body
                try {
                    val errorBody = response.body<BackendErrorResponse>()
                    Result.Error(
                        code = errorBody.code,
                        message = errorBody.description ?: errorBody.message,
                        errors = errorBody.errors
                    )
                } catch (e: Exception) {
                    // Fallback if parsing fails or body is empty
                    Result.Error(
                        code = "UNKNOWN_ERROR",
                        message = "An error occurred: ${response.status.value} ${response.status.description}"
                    )
                }
            }
        } catch (e: Exception) {
            Result.Error(
                code = "NETWORK_ERROR",
                message = e.localizedMessage ?: "Unknown network error"
            )
        }
    }
}
