package com.example.hilt.data.api

import com.example.hilt.data.model.responde.Product
import com.example.hilt.data.model.reuqest.User
import com.example.hilt.data.model.responde.Token
import retrofit2.Response

interface ApiHelper {
    // Function to make a user login API request
    suspend fun userLogin(user: User): Response<Token>

    // Function to retrieve a list of products
    suspend fun getProducts(): Response<List<Product>>
}
