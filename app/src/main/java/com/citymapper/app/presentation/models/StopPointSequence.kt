package com.citymapper.app.presentation.models

import android.os.Parcel
import android.os.Parcelable

data class StopPointSequence(val id: String,
                             var commonName: String?,
                             val distance: Double,
                             val lat: Double,
                             val lon: Double,
                             var hideTop: Boolean = true,
                             var hideCenter: Boolean = true,
                             var hideBottom: Boolean = true,
                             var clickedStation: Boolean = false,
                             var isTheNearest: Boolean = false)