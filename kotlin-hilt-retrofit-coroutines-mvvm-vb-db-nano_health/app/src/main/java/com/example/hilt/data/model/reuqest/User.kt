package com.example.hilt.data.model.reuqest

import com.squareup.moshi.Json

data class User(
    // Property representing the username
    @Json(name = "username")
    var username: String? = null,

    // Property representing the password
    @Json(name = "password")
    var password: String? = null
)
