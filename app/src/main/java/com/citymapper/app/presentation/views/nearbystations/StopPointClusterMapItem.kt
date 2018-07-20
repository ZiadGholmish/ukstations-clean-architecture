package com.citymapper.app.presentation.views.nearbystations

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class StopPointClusterMapItem(private val mPosition: LatLng, private val mTitle: String,
                              private val mSnippet: String) : ClusterItem {

    override fun getSnippet(): String {
        return mSnippet
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getPosition(): LatLng {
        return mPosition
    }
}