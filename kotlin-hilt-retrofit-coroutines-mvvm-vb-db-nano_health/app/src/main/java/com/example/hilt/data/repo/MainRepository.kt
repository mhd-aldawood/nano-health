package com.example.hilt.data.repo

import com.example.hilt.data.api.ApiHelper
import com.example.hilt.data.model.reuqest.User
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    // Function to make a user login API request using the injected ApiHelper instance
    suspend fun userLogin(user: User) = apiHelper.userLogin(user)

    // Function to retrieve a list of products using the injected ApiHelper instance
    suspend fun getProducts() = apiHelper.getProducts()

}
