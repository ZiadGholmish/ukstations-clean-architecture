package com.citymapper.app.presentation.models

import android.os.Parcel
import android.os.Parcelable

data class ArrivalTimeParcelable(val id: String,
                                 val lineName: String,
                                 val lineId: String,
                                 val expectedArrival: String,
                                 var direction: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(lineName)
        parcel.writeString(lineId)
        parcel.writeString(expectedArrival)
        parcel.writeString(direction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArrivalTimeParcelable> {
        override fun createFromParcel(parcel: Parcel): ArrivalTimeParcelable {
            return ArrivalTimeParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ArrivalTimeParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
