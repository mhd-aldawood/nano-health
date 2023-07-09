package com.example.hilt.data.api

import com.example.hilt.data.model.responde.Product
import com.mindorks.framework.mvvm.data.api.ApiService
import com.example.hilt.data.model.reuqest.User
import com.example.hilt.data.model.responde.Token
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    // Implementation of the userLogin function from the ApiHelper interface
    override suspend fun userLogin(user: User): Response<Token> =
        apiService.userLogin(user)

    // Implementation of the getProducts function from the ApiHelper interface
    override suspend fun getProducts(): Response<List<Product>> =
        apiService.getProducts()

}
