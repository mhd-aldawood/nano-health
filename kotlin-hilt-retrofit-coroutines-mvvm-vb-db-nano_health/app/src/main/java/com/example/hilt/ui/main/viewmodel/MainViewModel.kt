package com.example.hilt.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.hilt.data.model.responde.Token
import com.example.hilt.data.model.reuqest.User
import com.example.hilt.data.repo.MainRepository
import com.example.hilt.utils.NetworkHelper
import com.example.hilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // LiveData for users list
    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    // LiveData for error message
    var error: MutableLiveData<String> = MutableLiveData()

    // MutableLiveData for password and username input fields
    var password = MutableLiveData<String>()
    var username = MutableLiveData<String>()

    // MutableLiveData to trigger activity start
    var startActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    // Function to handle user login
    fun userLogin() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                // Create a User object with the input values
                val user = User(username = username.value, password = password.value)
                mainRepository.userLogin(user).let {
                    if (it.isSuccessful) {
                        val token = Token(it.body()?.token)
                        //TODO: Store this key in a secure storage (e.g., SharedPreferences)
                        startActivity.postValue(true) // Trigger the activity start
                    } else {
                        error.postValue(it.message()) // Post the error message
                    }
                }
            } else {
                error.postValue("Error No Network") // Post the error message
            }
        }
    }
}
