package com.example.hilt.data.model.responde

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Rating(
    // Property representing the rating value
    @Json(name = "rate")
    var rate: Double? = null,

    // Property representing the count of ratings
    @Json(name = "count")
    var count: Int? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        rate?.let { dest.writeDouble(it) }
        count?.let { dest.writeInt(it) }
    }

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }
    }
}
