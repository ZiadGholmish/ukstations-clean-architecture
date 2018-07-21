package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.Observer
import com.citymapper.app.R
import com.citymapper.app.app.AbsPresenter
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.data.remote.models.stops.NetworkHttpError
import com.citymapper.app.data.remote.models.stops.StopPoint
import com.citymapper.app.util.LocationUtil
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class NearbyStationsPresenter @Inject constructor() : AbsPresenter<NearbyStationsController>() {

    private lateinit var nearbyStationsVM: NearbyStationsVM

    fun initPresenter(nearbyStationsVM: NearbyStationsVM) {
        this.nearbyStationsVM = nearbyStationsVM
        setObservers()
    }

    fun fetchStopPoints(markerPosition: LatLng) {
        if (!LocationUtil.checkLocationInsideCountry(markerPosition)) {
            mView?.moveMapToDefaultLocation(LatLng(LocationUtil.defaultLat, LocationUtil.defaultLon))
        } else {
            nearbyStationsVM.loadStopPointsTest(markerPosition.latitude, markerPosition.longitude)
        }
    }

    /**
     * set the observers for the live data for the request state, data and errors
     */
    private fun setObservers() {
        nearbyStationsVM.stopPointsLiveData.observe(mView!!, Observer {
            showStopPoints(it.let { it } ?: listOf())
        })

        nearbyStationsVM.stopPointsNetworkHttpError.observe(mView!!, Observer {
            handleError(it.let { it } ?: NetworkHttpError.InternalServerError)
        })

        nearbyStationsVM.stopPointsRequestState.observe(mView!!, Observer {
            when (it!!) {
                is RequestState.Complete -> mView?.hideLoading()
                is RequestState.Idle -> mView?.hideLoading()
                is RequestState.Loading -> mView?.showLoading()
            }
        })
    }

    private fun showStopPoints(stopPoints: List<StopPoint>) {
        mView?.showStopPoints(stopPoints)
        //  mView?.zoomToStations(stopPoints.firstOrNull())
    }


    /**
     * handle the error code that come from the server
     */
    private fun handleError(result: NetworkHttpError) {
        when (result) {
            is NetworkHttpError.UnAuthorizedRequest -> mView?.showMessage(R.string.invalid_credential)
            is NetworkHttpError.InternalServerError -> mView?.showMessage(R.string.service_not_available)
            is NetworkHttpError.GeneralError -> mView?.showFetchingError("${result.message}")
        }
    }


}