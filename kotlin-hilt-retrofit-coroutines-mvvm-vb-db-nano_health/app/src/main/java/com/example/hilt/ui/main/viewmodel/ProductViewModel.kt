package com.example.hilt.ui.main.viewmodel

import androidx.lifecycle.*
import com.example.hilt.data.model.responde.Product
import com.example.hilt.data.repo.MainRepository
import com.example.hilt.utils.NetworkHelper
import com.example.hilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // MutableLiveData for error message
    var error: MutableLiveData<String> = MutableLiveData()

    // MutableLiveData to trigger activity start
    var startActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    // LiveData for products list
    private var _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>>
        get() = _products

    // Fetch products from the repository
    fun fetchProduct() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getProducts().let {
                    if (it.isSuccessful) {
                        _products.postValue(Resource.success(it.body()))
                    } else {
                        _products.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            }
        }
    }
}
