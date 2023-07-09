package com.example.hilt.data.model.responde

import com.squareup.moshi.Json

data class Token(
    // Property representing the authentication token
    @Json(name = "token")
    var token: String? = null
)
