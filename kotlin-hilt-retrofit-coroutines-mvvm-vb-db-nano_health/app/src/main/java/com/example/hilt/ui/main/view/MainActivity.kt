package com.example.hilt.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hilt.R
import com.example.hilt.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import android.Manifest
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.hilt.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the activity_main layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        // Request necessary permissions
        requestPermission()

        // Setup observers for the ViewModel
        setupObservers()
    }

    private fun requestPermission() {
        // Request INTERNET permission
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 101)

        // Request ACCESS_NETWORK_STATE permission
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE), 101)
    }

    private fun setupObservers() {
        // Observe the error LiveData and show a Toast with the error message
        mainViewModel.error.observe(this, Observer { t ->
            Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
        })

        // Observe the startActivity LiveData and start ProductsActivity when true
        mainViewModel.startActivity.observe(this, Observer { t ->
            if (t) {
                startActivity(Intent(this, ProductsActivity::class.java))
            }
        })
    }
}
