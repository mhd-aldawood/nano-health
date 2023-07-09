package com.example.hilt.ui.main.interfaces

import com.example.hilt.data.model.responde.Product

interface OnItemClickListener {
    // Function to handle the click event on a product item
    fun onClick(product: Product)
}
