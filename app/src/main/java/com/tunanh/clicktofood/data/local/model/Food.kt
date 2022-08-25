package com.tunanh.clicktofood.data.local.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Food(
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val cost: Int = 0,
    val star: Double? = 0.0,
    val img: String? = "",
    var amount:Int=1
):Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}
