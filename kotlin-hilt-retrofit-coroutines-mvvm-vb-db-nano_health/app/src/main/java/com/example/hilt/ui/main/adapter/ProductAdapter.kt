package com.example.hilt.ui.main.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.data.model.responde.Product
import com.example.hilt.databinding.ProductLayoutBinding
import kotlinx.android.synthetic.main.product_layout.view.*
import com.bumptech.glide.Glide
import com.example.hilt.ui.main.interfaces.OnItemClickListener

class ProductAdapter(private val products: ArrayList<Product>, val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ProductAdapter.DataViewHolder>() {

    // Create and inflate the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        ProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(products[position], onItemClickListener)

    // Return the number of items in the list
    override fun getItemCount(): Int = products.size

    // Add new data to the list
    fun addData(list: List<Product>) {
        products.addAll(list)
    }

    // ViewHolder class to hold and bind the views
    class DataViewHolder(itemView: ProductLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {

        // Bind data to the views
        fun bind(product: Product, itemClickListener: OnItemClickListener) {

            // Load the image using Glide library
            Glide.with(itemView.image_view.context)
                .load(product.image)
                .into(itemView.image_view)

            // Set the price text
            itemView.price.text = product.price.toString() + "Aed"

            // Set the rating value and color
            itemView.rank.rating = (product.rating?.rate!!?.toFloat())
            itemView.rank.progressDrawable.setColorFilter(
                Color.parseColor("#FFE072"),
                PorterDuff.Mode.SRC_ATOP
            )

            // Set the title text
            itemView.title.text = product.title.toString()

            // Set the description text
            itemView.description.text = product.description.toString()

            // Set click listener to the item view
            itemView.setOnClickListener(View.OnClickListener { itemClickListener.onClick(product) })
        }
    }
}
