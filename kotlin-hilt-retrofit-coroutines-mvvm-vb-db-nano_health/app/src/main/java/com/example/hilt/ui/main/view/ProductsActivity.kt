package com.example.hilt.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt.R
import com.example.hilt.data.model.responde.Product
import com.example.hilt.databinding.ActivityProductsBinding
import com.example.hilt.ui.main.adapter.ProductAdapter
import com.example.hilt.ui.main.view.divider.SimpleDividerItemDecoration
import com.example.hilt.ui.main.interfaces.OnItemClickListener
import com.example.hilt.ui.main.viewmodel.ProductViewModel
import com.example.hilt.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity(), OnItemClickListener {

    // Initialize the ProductViewModel using viewModels delegate
    private val productViewModel: ProductViewModel by viewModels()

    // Initialize the binding variable
    lateinit var binding: ActivityProductsBinding

    // Initialize the adapter
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products)
        binding.productViewModel = productViewModel
        binding.lifecycleOwner = this

        // Set the toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Setup the UI components
        setupUI()

        // Setup the observer for fetching product list
        setupObserver()

        // Fetch the product list
        getProductsList()
    }

    private fun getProductsList() {
        productViewModel.fetchProduct()
    }

    private fun setupUI() {
        // Set the RecyclerView layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize and set the adapter to the RecyclerView
        adapter = ProductAdapter(arrayListOf(), this)
        binding.recyclerView.addItemDecoration(SimpleDividerItemDecoration(this))
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        // Observe the products LiveData and update the UI accordingly
        productViewModel.products.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { products -> renderList(products) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    // Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(product: List<Product>) {
        // Add the product list to the adapter and update the RecyclerView
        adapter.addData(product)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(product: Product) {
        // Handle the click event on a product item
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("obj", product)
        startActivity(intent)
    }
}
