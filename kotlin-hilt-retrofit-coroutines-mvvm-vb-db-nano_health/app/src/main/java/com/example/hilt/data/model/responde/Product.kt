package com.example.hilt.data.model.responde

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import java.io.Serializable

data class Product(
    // Property representing the ID of the product
    @Json(name = "id")
    var id: Int? = null,

    // Property representing the title of the product
    @Json(name = "title")
    var title: String? = null,

    // Property representing the price of the product
    @Json(name = "price")
    var price: Double? = null,

    // Property representing the description of the product
    @Json(name = "description")
    var description: String? = null,

    // Property representing the category of the product
    @Json(name = "category")
    var category: String? = null,

    // Property representing the image URL of the product
    @Json(name = "image")
    var image: String? = null,

    // Property representing the rating of the product
    @Json(name = "rating")
    var rating: Rating? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(Rating::class.java.classLoader) ?: Rating(0.0, 0)
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        id?.let { dest.writeInt(it) }
        dest.writeString(title)
        price?.let { dest.writeDouble(it) }
        dest.writeString(description)
        dest.writeString(category)
        dest.writeString(image)
        dest.writeParcelable(rating, flags)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
