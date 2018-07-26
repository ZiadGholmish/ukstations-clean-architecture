package com.citymapper.app.presentation.models

import android.os.Parcel
import android.os.Parcelable

data class StopPointSequenceParcelable(val id: String,
                                       var commonName: String?,
                                       val distance: Double,
                                       val lat: Double,
                                       val lon: Double,
                                       var hideTop: Boolean = true,
                                       var hideCenter: Boolean = true,
                                       var hideBottom: Boolean = true) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(commonName)
        parcel.writeDouble(distance)
        parcel.writeDouble(lat)
        parcel.writeDouble(lon)
        parcel.writeByte(if (hideTop) 1 else 0)
        parcel.writeByte(if (hideCenter) 1 else 0)
        parcel.writeByte(if (hideBottom) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StopPointSequenceParcelable> {
        override fun createFromParcel(parcel: Parcel): StopPointSequenceParcelable {
            return StopPointSequenceParcelable(parcel)
        }

        override fun newArray(size: Int): Array<StopPointSequenceParcelable?> {
            return arrayOfNulls(size)
        }
    }
}