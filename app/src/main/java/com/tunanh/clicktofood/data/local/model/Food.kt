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
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readInt()
    )

    override fun describeContents(): Int =0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(title)
        dest.writeInt(cost)
        if (star != null) {
            dest.writeDouble(star)
        }
        dest.writeString(img)
        dest.writeInt(amount)
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}
