package com.mindorks.framework.mvvm.data.api

import com.example.hilt.data.model.responde.Product
import com.example.hilt.data.model.reuqest.User
import com.example.hilt.data.model.responde.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Function to make a user login API request
    @POST("/auth/login")
    suspend fun userLogin(@Body user: User): Response<Token>

    // Function to retrieve a list of products
    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>

}
