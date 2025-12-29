package com.tourly.app.core.network.util

import java.io.IOException

class APIException(
    val statusCode: Int,
    message: String
) : IOException("API Error $statusCode: $message")
